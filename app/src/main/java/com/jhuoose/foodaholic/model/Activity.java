package com.jhuoose.foodaholic.model;

public class Activity {
    String title = null;
    int votes = 0;

    public Activity() {}

    public Activity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
