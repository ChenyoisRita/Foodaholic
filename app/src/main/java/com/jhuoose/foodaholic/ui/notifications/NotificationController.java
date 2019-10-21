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
    static public List<Event> list_public_event = new ArrayList<>();
    private FirebaseDatabase database;
    public final Object lock = new Object();

    public NotificationController() {
        //this.database = database;.
        //public_event.setEvent_Location("Home");
        //public_event.setStart_Time("");
        //public_event.setEvent_Title("Mou Test");

    }

    public Notification GetNotificationFromEvent(Event event) {
        Notification tmp_notification = new Notification();
        tmp_notification.setStartTime(event.getStartTime());
        tmp_notification.setDate(event.getDate());
        tmp_notification.setEventTitle(event.getTitle());
        tmp_notification.setLocation(event.getLocation());
        return tmp_notification;
    }

    public Notification getDataFromFirebase(String eventTitle){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = rootRef.child("Events");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get user information
                //Log.d("Start Time: ", dataSnapshot.child("15da22d").getValue(Event.class).getTitle());
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Event tmp_event = child.getValue(Event.class);
                    if(tmp_event.getDate().substring(0,5).equals("10/23") ) {
                        list_public_event.add(child.getValue(Event.class));
                    }
                    Log.d("Start Time: ", tmp_event.getDate().substring(0,5));
                    Log.d("Start Time: ", child.getValue(Event.class).getTitle());
                }
                //Event tmp_event = dataSnapshot.getValue(Event.class);
                Log.d("Start Time: ", list_public_event.toString());
                public_event = list_public_event.get(0);
                Log.d("Start Time: ", public_event.getDate());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Event event;
        event = public_event;
        Notification notification = GetNotificationFromEvent(event);
        return notification;
    }

}
