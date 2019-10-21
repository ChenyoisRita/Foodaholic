package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.ActivityAdapter;
import com.jhuoose.foodaholic.model.Activity;
import com.jhuoose.foodaholic.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDetailActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private String eid;
    private Event event;
    private List<Activity> activityList;

    private TextView eventTitle;
    private Button addActivityButton;
    private ListView activityListView;

    private ActivityAdapter activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        database = FirebaseDatabase.getInstance();

        eventTitle = (TextView) this.findViewById(R.id.event_view_title);
        activityListView = (ListView) this.findViewById(R.id.activity_list);

        event = new Event();
        activityList = new ArrayList<>();

        Intent intent = getIntent();
        eid = intent.getStringExtra("eid");
        DatabaseReference eventRef = database.getReference("Events").child(eid);
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                event = dataSnapshot.getValue(Event.class);
                eventTitle.setText(event.getTitle());
                activityAdapter = new ActivityAdapter(event.getActivityList(), EventDetailActivity.this);
                activityListView.setAdapter(activityAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Activity testActivity = new Activity("test");
//        activityList.add(testActivity);
//        activityAdapter = new ActivityAdapter(activityList, EventDetailActivity.this);
//        activityListView.setAdapter(activityAdapter);
    }
}
