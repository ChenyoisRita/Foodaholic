package com.jhuoose.foodaholic.model;

public class Notification {
    private String eventTitle;
    private String startTime;
    private String location;

    public Notification(){

    }

    public Notification(String eventTitle, String startTime, String loction) {
        this.eventTitle = eventTitle;
        this.startTime = startTime;
        this.location = loction;
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
}
