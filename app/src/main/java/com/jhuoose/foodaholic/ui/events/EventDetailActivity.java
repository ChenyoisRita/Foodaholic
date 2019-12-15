package com.jhuoose.foodaholic.ui.events;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.ActivityAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.model.Activity;
import com.jhuoose.foodaholic.ui.MainActivity;
import com.jhuoose.foodaholic.viewmodel.ActivityProfile;
import com.jhuoose.foodaholic.viewmodel.Event;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    public static int eid;
    private Event event;
    private int flag=-1;
    private int payerID = -1;
    private List<ActivityProfile> activityList;
    Activity activity;

    private TextView eventTitle, totalPrice_tv;
    private ListView activityListView;
    private Button addActivity_btn, leaveEvent_btn, moreInfo_btn, splitBill_btn, inviteFriends_btn;

    private ActivityAdapter activityAdapter;
    String activityTitle, eventName;
    public Event eventInfo;
    public String entryCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        heroku = HerokuService.getAPI();

//        eventTitle = this.findViewById(R.id.event_view_title);
        activityListView = this.findViewById(R.id.activity_list);
        addActivity_btn = this.findViewById(R.id.add_activity_button);
        totalPrice_tv = this.findViewById(R.id.totalPrice_tx);
        leaveEvent_btn = this.findViewById(R.id.leaveEvent_button);
        moreInfo_btn = this.findViewById(R.id.moreInfo_button);
        splitBill_btn = this.findViewById(R.id.splitBill_button);
        inviteFriends_btn = this.findViewById(R.id.inviteFriends_button);

        activityList = new ArrayList<>();
        activityAdapter = new ActivityAdapter(activityList, EventDetailActivity.this);
        activityListView.setAdapter(activityAdapter);

        Intent intent = getIntent();
        eid = intent.getIntExtra("eventId",-1);
        eventName = intent.getStringExtra("eventName");

        updateActivityList();

        getEventInfo();

        getEntryCode();

        moreInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EventDetailActivity.this);
                builder.setTitle("Details of "+eventName)
                        .setMessage("ID: "+eventInfo.getId()+"\n"+
                                    "Name: "+eventName+"\n"+
                                    "Description: "+eventInfo.getDescription()+"\n"+
                                    "Location: "+eventInfo.getLocation()+"\n"+
                                    "Begin: "+eventInfo.getStartTime()+"\n"+
                                    "End: "+eventInfo.getEndTime()+"\n"+
                                    "Theme: "+eventInfo.getTheme()+"\n"+
                                    "EntryCode: "+entryCode)
                        .setPositiveButton("OK👌", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                // Create Dialog Box
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        addActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showAddDialog();
            }
        });

        leaveEvent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EventDetailActivity.this);
                builder.setMessage("Confirm to Leave Event: "+eventName+" ?")
                        .setTitle("⚠️"+"Confirmation")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Call<ResponseBody> call_leaveEvent = heroku.leaveEvent(eid);
                                call_leaveEvent.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (!response.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Leave Event Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Leave Event"+eventName+" Successfully",Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                // Create Dialog Box
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        splitBill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EventDetailActivity.this, SplitBillActivity.class);
                intent1.putExtra("eventId",eid);
                startActivity(intent1);
            }
        });

        inviteFriends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showInviteDialog();
            }

        });



        // Todo: set the total Price for totalPrice_tv.
        // totalPrice_tv.setText();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateActivityList();
    }

    private void updateActivityList() {
        Call<List<ActivityProfile>> call_getActivityList = heroku.getActivityList(eid);
        call_getActivityList.enqueue(new Callback<List<ActivityProfile>>() {
            @Override
            public void onResponse(Call<List<ActivityProfile>> call, Response<List<ActivityProfile>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(EventDetailActivity.this, "Fetch ActivityList error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    activityList.clear();
                    for (ActivityProfile activityProfile: response.body()) {
                        activityList.add(activityProfile);
                    }
                    activityAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ActivityProfile>> call, Throwable t) {
                Toast.makeText(EventDetailActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getEventInfo() {
        Call<Event> call_getEvent = heroku.getEvent(eid);
        call_getEvent.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Get Event Info Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    eventInfo = response.body();
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getEntryCode() {
        Call<ResponseBody> call_getEntryCode = heroku.getEntryCode(eid);
        call_getEntryCode.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Get EntryCode Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        entryCode = response.body().string();
                        Log.i("EntryCode", entryCode);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Convert Response Error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "EntryCode: Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected final void showAddDialog(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.activity_add_activity, null);
        final EditText editCategory = textEntryView.findViewById(R.id.edit_Category);
        final EditText editName = textEntryView.findViewById(R.id.edit_Name);
        final RadioGroup rg = textEntryView.findViewById(R.id.rg_choosePayer);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_myself:
                        payerID = MainActivity.currentUserID;
                        break;
                    case R.id.rb_organizer:
                        payerID = eventInfo.getOrganizer().getId();
                        break;
                }
            }
        });

        AlertDialog.Builder ad1 = new AlertDialog.Builder(EventDetailActivity.this);
        ad1.setTitle("Add a new activity:");
        ad1.setIcon(android.R.drawable.ic_dialog_info);
        ad1.setView(textEntryView);
        ad1.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        ad1.setNegativeButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String activityName = editName.getText().toString().trim();
                String description = " ";
                int vote = 0;
                float money = 0;
                String category = editCategory.getText().toString().trim();

                Log.i("AddActivity", "aName:"+activityName+"; cate:"+category+"; payer:"+payerID);
                if (payerID==-1 || activityName==null || category==null || activityName.equals("") || category.equals("")) {
                    Toast.makeText(getApplicationContext(), "Missing Input; Create Activity Failed", Toast.LENGTH_LONG).show();
                } else {
                    Call<ResponseBody> call = heroku.createActivity(eid, activityName, description, vote,
                            money, category, payerID);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(EventDetailActivity.this, "Add activity failed" + response.errorBody(), Toast.LENGTH_SHORT).show();
                                Log.i("AddActivityLog", "Response Unsuccessful: "+response.errorBody());
                            }
                            else{
                                Toast.makeText(EventDetailActivity.this, "Add activity Successfully", Toast.LENGTH_SHORT).show();
                                updateActivityList();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(EventDetailActivity.this, "Connection failed." +t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        ad1.show();// Display Dialog;

    }
    protected final void showInviteDialog(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.activity_invite_friends, null);
        final EditText editEmail = textEntryView.findViewById(R.id.edit_Email);
        AlertDialog.Builder ad1 = new AlertDialog.Builder(EventDetailActivity.this);
        ad1.setTitle("Please invite a friend:");
        ad1.setIcon(android.R.drawable.ic_dialog_info);
        ad1.setView(textEntryView);
        ad1.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        ad1.setNegativeButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String description = " ";
                int vote = 0;
                float money = 0;
                String email = editEmail.getText().toString();

                Call<ResponseBody> call_invite = heroku.sendEntryCodeTo(email, eid);
                        call_invite.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(EventDetailActivity.this, "Invite Friends failed" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(EventDetailActivity.this, "Invite Friends Successfully", Toast.LENGTH_SHORT).show();
                            updateActivityList();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(EventDetailActivity.this, "Connection failed." +t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        ad1.show();


    }


}
