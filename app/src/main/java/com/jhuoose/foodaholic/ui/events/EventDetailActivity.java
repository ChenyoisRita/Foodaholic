package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private TextView eventTitle, totalPrice_tv;
    private ListView activityListView;
    private Button addActivity_btn;

    private ActivityAdapter activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        database = FirebaseDatabase.getInstance();

        eventTitle = this.findViewById(R.id.event_view_title);
        activityListView = this.findViewById(R.id.activity_list);
        addActivity_btn = this.findViewById(R.id.add_activity_button);
        totalPrice_tv = this.findViewById(R.id.totalPrice_tx);

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

        addActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: after clicking this button, You can add an activity to current event.
            }
        });

        // Todo: set the total Price for totalPrice_tv.
        // totalPrice_tv.setText();
    }
}
