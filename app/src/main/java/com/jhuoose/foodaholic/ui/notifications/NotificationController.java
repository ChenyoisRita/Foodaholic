package com.jhuoose.foodaholic.ui.notifications;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jhuoose.foodaholic.model.Event;
import com.jhuoose.foodaholic.model.Notification;

import java.security.acl.NotOwnerException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class NotificationController {
    static public Event public_event = new Event();
    private FirebaseDatabase database;
    public final Object lock = new Object();

    public NotificationController() {
        //this.database = database;.

    }

    public Notification GetNotificationFromEvent(Event event) {
        Notification tmp_notification = new Notification();
        tmp_notification.setStartTime(event.getStart_Time());
        tmp_notification.setEventTitle(event.getEvent_Title());
        tmp_notification.setLocation(event.getEvent_Location());
        return tmp_notification;
    }

    public Notification getDataFromFirebase(String eventTitle){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = rootRef.child("Events").child(eventTitle);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get user information
                Event tmp_event = dataSnapshot.getValue(Event.class);
                public_event = tmp_event;
                //Log.d("Start Time: ", dataSnapshot.child("start_Time").getValue(String.class));
                Log.d("Start Time: ", tmp_event.getStart_Time());
                Log.d("Start Time: ", public_event.getStart_Time());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //event.setEvent_Location("Home");
        //event.setStart_Time(startTime);
        //event.setEvent_Title("Mou Test");
        Event event;
        event = public_event;
        Notification notification = GetNotificationFromEvent(event);
        return notification;
    }

}
