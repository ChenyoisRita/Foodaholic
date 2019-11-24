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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.ActivityAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.model.Activity;
import com.jhuoose.foodaholic.viewmodel.Event;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    int eid;
    private Event event;
    private List<Activity> activityList;
    Activity activity;

    private TextView eventTitle, totalPrice_tv;
    private ListView activityListView;
    private Button addActivity_btn;

    private ActivityAdapter activityAdapter;
    String activityTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        heroku = HerokuService.getAPI();

        eventTitle = this.findViewById(R.id.event_view_title);
        activityListView = this.findViewById(R.id.activity_list);
        addActivity_btn = this.findViewById(R.id.add_activity_button);
        totalPrice_tv = this.findViewById(R.id.totalPrice_tx);

        activityList = new ArrayList<>();

        Intent intent = getIntent();
        eid = intent.getIntExtra("eventId",-1);

        initializeaActivityList();

        addActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: after clicking this button, You can add an activity to current event.
            showAddDialog();
//            event.getActivityList().add(activity);
            }
        });

        // Todo: set the total Price for totalPrice_tv.
        // totalPrice_tv.setText();
    }

    private void initializeaActivityList() {
        Call<Event> call_get = heroku.getEvent(eid);
        call_get.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(EventDetailActivity.this, "Add event failed" + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
                else{
//                    startActivity(new Intent(EventDetailActivity.this, EventsFragment.class));
                    event = response.body();
                    eventTitle.setText(event.getEventName());
                    activityAdapter = new ActivityAdapter(event.getActivityList(),EventDetailActivity.this);
                    activityListView.setAdapter(activityAdapter);

                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(EventDetailActivity.this, "Connection failed." +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected final void showAddDialog(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.activity_add_activity, null);
        final EditText editCategory = (EditText) textEntryView.findViewById(R.id.edit_Category);
        final EditText editName = (EditText)textEntryView.findViewById(R.id.edit_Name);
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
                Map<String, Object> map = new HashMap<>();
                activity = new Activity();
                activity.serCategory(editCategory.getText().toString());
                activityTitle = editName.getText().toString();
                activity.setTitle(activityTitle);
                map.put(activityTitle, activity);
                Call<ResponseBody> call = heroku.createActivity(eid, map);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(EventDetailActivity.this, "Add activity failed" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }
                        else{
//                              startActivity(new Intent(AddEventActivity.this, EventsFragment.class));
                            Toast.makeText(EventDetailActivity.this, "Add activity Successfully", Toast.LENGTH_SHORT).show();
                            initializeaActivityList();
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
        ad1.show();// Display Dialog;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        initializeaActivityList();
//    }
}
