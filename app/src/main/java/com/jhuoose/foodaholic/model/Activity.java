package com.jhuoose.foodaholic.model;

public class Activity {
    String title = null;
    int votes = 0;
    String price = null;

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

    public String getPrice() { return price;}

    public void setPrice(String price) {this.price = price;}
}
