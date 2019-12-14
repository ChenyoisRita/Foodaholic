package com.jhuoose.foodaholic.viewmodel;

import java.util.ArrayList;

public class Activity {
    private int id;
    private String activityName;
    private String description;
    private int vote;
    private double money;
    private String category;
    private EventProfile event;
    private ArrayList<UserProfile> voteList = new ArrayList<>();
    private ArrayList<UserProfile> booList = new ArrayList<>();
    private ArrayList<UserProfile> participantList = new ArrayList<>();
    private UserProfile payer;

    public Activity() {
    }

    public Activity(Activity activity,
                            EventProfile event,
                            ArrayList<UserProfile> voteList,
                            ArrayList<UserProfile> booList,
                            ArrayList<UserProfile> participantList,
                            UserProfile payer) {
        this.id = activity.getId();
        this.activityName = activity.getActivityName();
        this.description = activity.getDescription();
        this.vote = activity.getVote();
        this.money = activity.getMoney();
        this.category = activity.getCategory();
        this.event = event;
        this.voteList = voteList;
        this.booList = booList;
        this.participantList = participantList;
        this.payer = payer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<UserProfile> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(ArrayList<UserProfile> participantList) {
        this.participantList = participantList;
    }

    public EventProfile getEvent() {
        return event;
    }

    public void setEvent(EventProfile event) {
        this.event = event;
    }

    public ArrayList<UserProfile> getVoteList() {
        return voteList;
    }

    public void setVoteList(ArrayList<UserProfile> voteList) {
        this.voteList = voteList;
    }

    public ArrayList<UserProfile> getBooList() {
        return booList;
    }

    public void setBooList(ArrayList<UserProfile> booList) {
        this.booList = booList;
    }

    public UserProfile getPayer() {
        return payer;
    }

    public void setPayer(UserProfile payer) {
        this.payer = payer;
    }
}
