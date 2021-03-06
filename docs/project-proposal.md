# Foodahlic

A party planner, or a general event planner.

# Elevator Pitch

We are building a mobile Android app where people can join an event such as a session, a party and a picnic, choose appropriate places, coming up with their favorite food, vote (or boo) other's choices or ideas, chat with others, and finally split the bill.

# Problem

- Discussing the location for holding any events, like a party or meeting, might be a nightmare for both organizers and participants. 
- Discussing the coming activities which fit for most people's taste in an event with all the participants is difficult.
- Notifying the bill of the event to all the participants one by one is time consuming, organizers need an effective and efficient way to do that.
- Only telling people the location, like "789 East 66th Street" is not intuitive, organizers might want to display the location directly.

## Introduction to Domain

We’re working in a domain that is well understood by a broader audience

# Solution
- Our app would serve as a platform on which organizers are able to hold an event with mobile phone.
- For the organizers, they can decide where and when the event holds, while leaving food and activity to choose by participants.
- For the participants, they can vote to decide what to eat and what to do during the event.
- This app will reduce the complexity of preparing for the party for organizers. At the mean time, particapants are able to DIY their own experience in the party by voting food and activity online.

## Architecture Overview

We are building a mobile app.
- Frontend: Android
- Backend: Java
- Database: Firebase
- API: Google Maps, Google Calender, Gmail
- Tools: Android Studio.

## Features

- Party Organizers
	- Create an event
	- Set place and time
	- Decide final activities(food, etc.)
	- Approve requests to join the event
	- Set & split the bill
	- Cancel & end an event
- Users
	- Identifier
	- Search or browse events
	- Join an event
	- Send invitations
	- Add activity proposals
	- Vote or boo other's proposals
	- Chat

## Wireframes
**Events Page**
<div align=center>
<img src="/pictures/Events-page.png" 
    width="360" alt="events_page" />
</div> <br>
<br>

**Create Events**<br>
<div align=center>
<img src="/pictures/Events%26Create_Page.png" 
    width="1000" alt="create_Events_page" />
</div> <br>
<br>

**Chats & Split Bill Page**<br>
<div align=center>
<img src="/pictures/Chats.png" 
    width="720" alt="chats_split_page" />
</div> <br>
<br>

**Friends & Profile Page**<br>
<div align=center>
<img src="/pictures/Friends-Profile.png" 
    width="720" alt="friends_profile_page" />
</div> <br>


## User Stories

As an orginizer, I want to create a party for my birthday celebration. I first sign up and log in to my account in Foodaholic, then create a party by selecting the time and place, food list, and the party themes that I prefer. A invitation code of the party is generated, and I can send it to my friends to invite them to join in. 

As a user, I want to join a party of my friends. By viewing Foodaholic, I can browse the upcoming events nearby, find my friends' event and join them. I can come up with my ideas about the activities in the party. I can also view other's ideas and vote for them, and the final decision is upon the organizer. In the app, I can hat and comment on the party, and when the party ends, we shall split the bill.

# Viability

## Hardware

We are developing an Android application, so we don't have the hardware.

## APIs

- Integration with Google Calendar API for adding events directly to user's calendar.
- Integration with Google Map API for displaying the exact location of the events.
- Integration with JavaMail API for building mails and messaging users of this application, like bill info or event news notifications.<br>
All those APIs above are free.

## Tools

Frontend: Android Studio<br>
Backend: Java<br>
Database: SQLite<br>
Others: tools in Toolbox.

## Proof of Concept

Our project is technically viable. <br>
For the development of this application in Android, we can use Android Studio to complete the UI design and program for interactive functions and other necessary methods of client in Java. For the backend services of server, we can use SQLite to store users profile related information, events details and might even store the chatting logs, photos or videos of chatting. Payment notification function can be integrated by JavaMail API. As for the location details of an event, it can be implemented by Google Map API and upcoming event notification can be realized via Google Calendar API. All in all, our project is technically viable.

# Difficulty

To complete this project, we need to build an mobile application running in Android phones. We need to integrated Google Maps API into this project to display the exact location of an event on the main page, so that users or event organizers could have a direct impression of where to go. We need to investigate how to display a specific location on an Android app with this API. And the integration of Google Calendar is also required in order that users can add the upcoming event to their Google calendar and get notifications for that. Investigation of adding a calendar file with specific date, time and location to user's google calendar app is also a challenge.<br>

Also, the communication from the front end (mobile app) to the backend (server) will be a challenge for us. Furthermore, using JavaMail API to send emails to participants for informing them with the bill and event details would be challenging as well. Because nobody of our team have integrated this API to Apps before and we think that it would be a little bit complex as the result of involving generating an email and ensuring that it can be sent to the destinations correctly.<br>

Generally speaking, the project we are proposing will be neither hard nor easy and it will be at the right scale.

# Market Research

## Users

The users of this application would be people who need to attend some organized events like party and seminar. It's really convenient and helpful because this application provides a platform to allows people to get the real-time events information and search nearby events information online, join the events just by phone, select favorite food, chat with others, comment on the food and activities, and even organize or customize an event.

## Competition

Twitter is similar to our projet. Twitter allows people to know about the real-time events that they can join and track the event information. Different from the Twitter, our project helps people organize and join the event and order the food based on the food ranking and comments. There are many other features and functions such as chatting with others. It's not limited to knowing about the events information.

# Roadmap

https://github.com/jhu-oose/2019-group-foodaholic/projects

