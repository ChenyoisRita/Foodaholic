package com.jhuoose.foodaholic.model;

public class Food {
    String foodName = null;
    int votes = 0;

    public Food() {}

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public Food() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
