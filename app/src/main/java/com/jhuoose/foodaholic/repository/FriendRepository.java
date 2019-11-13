package com.jhuoose.foodaholic.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jhuoose.foodaholic.model.Event;
import com.jhuoose.foodaholic.model.Friend;

public class FriendRepository {
    private static final FriendRepository ourInstance = new FriendRepository();

    private FirebaseDatabase database;

    private Friend buffer;

    public static FriendRepository getInstance() {
        return ourInstance;
    }

    private FriendRepository() {
        this.database = FirebaseDatabase.getInstance();
    }

    public void saveFriend(Friend friend){
        // URL to Firebase user data json
        DatabaseReference myRef = database.getReference("Friends");
        myRef.child(friend.getUid()).setValue(friend);
    }
}
