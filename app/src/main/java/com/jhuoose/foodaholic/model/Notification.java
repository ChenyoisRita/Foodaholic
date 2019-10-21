package com.jhuoose.foodaholic.model;

public class Notification {
    private String eventTitle;
    private String startTime;
    private String location;
    private String date;

    public Notification(){

    }

    public Notification(String eventTitle, String startTime, String location, String date) {
        this.eventTitle = eventTitle;
        this.startTime = startTime;
        this.location = location;
        this.date = date;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
