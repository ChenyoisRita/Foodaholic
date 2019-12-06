package com.jhuoose.foodaholic.ui.events;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.jhuoose.foodaholic.viewmodel.ActivityProfile;
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
    private List<ActivityProfile> activityList;
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
        activityAdapter = new ActivityAdapter(activityList, EventDetailActivity.this);
        activityListView.setAdapter(activityAdapter);

        Intent intent = getIntent();
        eid = intent.getIntExtra("eventId",-1);

        updateActivityList();

        addActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: after clicking this button, You can add an activity to current event.
            showAddDialog();
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
                String activityName = editName.getText().toString();
                String description = " ";
                int vote = 0;
                float money = 0;
                String category = editCategory.getText().toString();

                Call<ResponseBody> call = heroku.createActivity(eid, activityName, description, vote,
                        money, category);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(EventDetailActivity.this, "Add activity failed" + response.errorBody(), Toast.LENGTH_SHORT).show();
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
        });
        ad1.show();// Display Dialog;
    }
}
