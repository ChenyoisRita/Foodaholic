package com.jhuoose.foodaholic.model;

import java.util.List;

public class Event {
    private String eid;
    private String title;
    private String description;
    private String location;
    private String startTime, endTime;
    private String date;
    private String theme;
    private String organizerUid; //Organizer UID
    private List<String> participantList;
    private List<Activity> activityList;
//    public String start_Time, end_Time, event_Date, event_Title, event_Location, event_notes, selectedEventTheme;
//    public ArrayList<Activity> foodArrayList;
//    public ArrayList<String> attendeeList;

    public Event() {
        // Default constructor required for calls to DataSnapshot.getValue(Event.class)
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getOrganizerUid() {
        return organizerUid;
    }

    public void setOrganizerUid(String organizerUid) {
        this.organizerUid = organizerUid;
    }

    public List<String> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<String> participantList) {
        this.participantList = participantList;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }
}
