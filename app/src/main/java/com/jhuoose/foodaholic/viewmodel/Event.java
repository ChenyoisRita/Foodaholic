package com.jhuoose.foodaholic.viewmodel;

import java.util.ArrayList;

public class Event {
    private int id;
    private String eventName;
    private String description;
    private String location;
    private String startTime;
    private String endTime;
    private UserProfile organizer;
    private String theme;
    private ArrayList<UserProfile> participantList = new ArrayList<>();
    private ArrayList<ActivityProfile> activityList = new ArrayList<>();

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public UserProfile getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserProfile organizer) {
        this.organizer = organizer;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public ArrayList<UserProfile> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(ArrayList<UserProfile> participantList) {
        this.participantList = participantList;
    }

    public ArrayList<ActivityProfile> getActivityList() {
        return activityList;
    }

    public void setActivityList(ArrayList<ActivityProfile> activityList) {
        this.activityList = activityList;
    }
}
