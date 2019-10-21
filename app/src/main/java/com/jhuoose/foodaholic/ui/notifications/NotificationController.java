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

import static android.content.ContentValues.TAG;

public class NotificationController {
    private FirebaseDatabase database;

    public NotificationController() {
        //this.database = database;.

    }

    public Notification GetNotificationFromEvent(Event event) {
        Notification tmp_notification = new Notification();
        tmp_notification.setEventTitle(event.getEvent_Title());
        tmp_notification.setLocation(event.getEvent_Location());
        tmp_notification.setStartTime(event.getStart_Time());
        return tmp_notification;
    }

    public Notification getDataFromFirebase(){
        Event event = new Event();
        event.setEvent_Location("Home");
        event.setStart_Time("Now");
        event.setEvent_Title("Mou Test");
        Notification notification = GetNotificationFromEvent(event);
        return notification;
    }

}
