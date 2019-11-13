package com.jhuoose.foodaholic.controller;

import com.jhuoose.foodaholic.model.Friend;
import com.jhuoose.foodaholic.repository.FriendRepository;

public class FriendController {
    private static final FriendController ourInstance = new FriendController();
    private FriendRepository friendRepository;

    public static FriendController getInstance(){
        return ourInstance;
    }

    private FriendController(){
        this.friendRepository = FriendRepository.getInstance();
    }

    public void createFriend(Friend friend){
        friend.setUid(Integer.toHexString(friend.hashCode()));
        friendRepository.saveFriend(friend);
    }

    public void updateFriend(Friend friend){
        friendRepository.saveFriend(friend);
    }
}
