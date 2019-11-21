package com.jhuoose.foodaholic.viewmodel;

import java.util.ArrayList;

public class User {
    private int id;
    private String email;
    private String userName;
    private String photoURL;
    private String phone;
    private ArrayList<UserProfile> friendList = new ArrayList<>();
    private ArrayList<EventProfile> participatingEventList = new ArrayList<>();
    private ArrayList<Integer> notificationList = new ArrayList<>(); //TODO: to be changed to NotificationProfileView

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<UserProfile> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<UserProfile> friendList) {
        this.friendList = friendList;
    }

    public ArrayList<EventProfile> getParticipatingEventList() {
        return participatingEventList;
    }

    public void setParticipatingEventList(ArrayList<EventProfile> participatingEventList) {
        this.participatingEventList = participatingEventList;
    }

    public ArrayList<Integer> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(ArrayList<Integer> notificationList) {
        this.notificationList = notificationList;
    }
}
