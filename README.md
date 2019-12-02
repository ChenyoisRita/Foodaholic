# Foodaholic

A general event planner.

The android application consists of two parts, the server and the mobile app. The server, in the server branch, is deployed on https://foodaholic-server.herokuapp.com/ (a nearly blank admin page, serving as the domain of this project). For more things about the server, please see the README.md in the server branch.

This master branch works as the main directory of the mobile client.

This README.md will be currently organized as a development list of different parts.

## Iteration 5

In this itera

## Client

- LoginActivity
    - RegisterActivity
- MainActivity
    - EventsFragment
        - AddEventActivity
            - StartTimePickerDialog
            - EndTimePickerDialog #TODO: Could you merge them?
        - EventDetailActivity
            - SetFoodListActivity #TODO: Change it to AddActivityDialog
        - SearchEventActivity
    - NotificationsFragment
        - NotificationDetailActivity
    - FriendsFragment
        - FriendDetailActivity
        - SearchFriendActivity
    - HomeFragment

## Interface

- User
    - createUser
    - editUser
    - deleteUser
    - login
    - logout
    - getCurrentUserEmail
    - getCurrentUserProfile
    - getFriendProfile
    - getUnrelatedProfile
    - sendAddFriendRequest
    - addFriend
    - deleteFriend
    - getFriendList
    - sendJoinEventRequest
    - getParticipatingEvent
    - leaveEvent
    - searchUserByEmail
    - searchUserByName
    - suggestFriends
    - getNotification
- Event
    - createEvent
    - editEvent
    - closeEvent
    - deleteEvent
    - getOrganizer
    - resetOrganizer
    - getParticipants
    - addParticipant
    - deleteParticipant
    - inviteParticipant
    - sendNotification
    - addActivity
    - getActivity
    - editActivity
    - deleteActivity
    - searchEventByName
    - searchEventByTime
    - suggestEvents

