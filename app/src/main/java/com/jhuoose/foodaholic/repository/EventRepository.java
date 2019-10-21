package com.jhuoose.foodaholic.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhuoose.foodaholic.model.Event;

public class EventRepository {
    private static final EventRepository ourInstance = new EventRepository();

    private FirebaseDatabase database;

    private Event buffer;

    public static EventRepository getInstance() {
        return ourInstance;
    }

    private EventRepository() {
        this.database = FirebaseDatabase.getInstance();
    }

    public void saveEvent(Event event){
        // URL to Firebase user data json
        DatabaseReference myRef = database.getReference("Events");
        myRef.child(event.getEid()).setValue(event);
    }
}
