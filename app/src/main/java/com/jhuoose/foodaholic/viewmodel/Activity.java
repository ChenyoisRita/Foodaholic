package com.jhuoose.foodaholic.viewmodel;

import java.util.ArrayList;

public class Activity {
    private int id;
    private String activityName;
    private String description;
    private int vote;
    private float money;
    private String category;
    private ArrayList<UserProfile> participantList = new ArrayList<>();

    public Activity() {
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
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
}
