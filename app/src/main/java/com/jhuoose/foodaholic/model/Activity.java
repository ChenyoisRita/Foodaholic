package com.jhuoose.foodaholic.model;

public class Activity {
    String title = null;
    int votes = 0;
    String price = null;
    String category = null;
  //  String name = null;

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

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

  //  public String getName () {return name;}

   // public void setName (String name) {this.name = name;}


}
