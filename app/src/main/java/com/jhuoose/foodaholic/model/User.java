package com.jhuoose.foodaholic.model;

import java.util.List;

public class User{
    private String uid;
    private String email;
    private String photoURL;
    private String phone;
    private List<String> friendList;
    private List<String> eidlist; //Event ID List;

    private static User currentUser;

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }

    public List<String> getEidlist() {
        return eidlist;
    }

    public void setEidlist(List<String> eidlist) {
        this.eidlist = eidlist;
    }
}
