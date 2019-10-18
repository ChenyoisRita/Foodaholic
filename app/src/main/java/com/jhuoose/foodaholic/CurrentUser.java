package com.jhuoose.foodaholic;

import com.jhuoose.foodaholic.ui.events.Event;

import java.util.ArrayList;

/**
 * This class is Temporarily not in use.
 */
public class CurrentUser {
    // After the user login successful, We can get all the events created by him
    // To complete that
    // 1. We need to know who is the current login user. This class is used to store the info of current user;
    // 2. We can get this user's events by him email address.
    public String username;
    public ArrayList<Event> EventList = new ArrayList<>();

    public CurrentUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        EventList = eventList;
    }
}
