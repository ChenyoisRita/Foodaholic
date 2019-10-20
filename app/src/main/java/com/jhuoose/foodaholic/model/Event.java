package com.jhuoose.foodaholic.model;

import java.util.ArrayList;

public class Event {
    public String start_Time, end_Time, event_Date, event_Title, event_Location, event_notes, selectedEventTheme;
    public ArrayList<Food> foodArrayList;

    public Event() {
        // Default constructor required for calls to DataSnapshot.getValue(Event.class)
    }

    public Event(String start_Time, String end_Time, String event_Date, String event_Title, String event_Location, String event_notes, String selectedEventTheme, ArrayList<Food> foodArrayList) {
        this.start_Time = start_Time;
        this.end_Time = end_Time;
        this.event_Date = event_Date;
        this.event_Title = event_Title;
        this.event_Location = event_Location;
        this.event_notes = event_notes;
        this.selectedEventTheme = selectedEventTheme;
        this.foodArrayList = foodArrayList;
    }

    public void setStart_Time(String start_Time) {
        this.start_Time = start_Time;
    }

    public void setEnd_Time(String end_Time) {
        this.end_Time = end_Time;
    }

    public void setEvent_Date(String event_Date) {
        this.event_Date = event_Date;
    }

    public void setEvent_Title(String event_Title) {
        this.event_Title = event_Title;
    }

    public void setEvent_Location(String event_Location) {
        this.event_Location = event_Location;
    }

    public void setEvent_notes(String event_notes) {
        this.event_notes = event_notes;
    }

    public String getStart_Time() {
        return start_Time;
    }

    public String getEnd_Time() {
        return end_Time;
    }

    public String getEvent_Date() {
        return event_Date;
    }

    public String getEvent_Title() {
        return event_Title;
    }

    public String getEvent_Location() {
        return event_Location;
    }

    public String getEvent_notes() {
        return event_notes;
    }


    public ArrayList<Food> getFoodArrayList() {
        return foodArrayList;
    }

    public void setFoodArrayList(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }

}
