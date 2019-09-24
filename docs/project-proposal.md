# Foodahlic

A party planner, or a general event planner.

# Elevator Pitch

We are building a mobile app where people can join a party, choose places, coming up with their favorite dishes, vote (or boo) other's ideas, chat with others, and finally split the bill.

# Problem

Complexity in planning and joining a party

## Introduction to Domain

We’re working in a domain that is well understood by a broader audience

# Solution

Organizers will use our app to plan a party online, which will reduce the complexity of preparing for the party. At the mean time, particapants are able to DIY their own experience in the party by voting food and activity online.

## Architecture Overview

We are building a a mobile app.
- Frontend: Android
- Backend: Java
- Database: SQLite
- API: Google Maps, Google Calender, Gmail, Paypal
- Tools: tools in Toolbox, Android Studio.

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
	- Pay the bill

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
<img src="https://raw.githubusercontent.com/jhu-oose/2019-group-foodaholic/develop/pictures/Chats.png?token=AJEU26HS2RM4GDRSJV3WMH25QJIAC" 
    width="720" alt="chats_split_page" />
</div> <br>
<br>

**Friends & Profile Page**<br>
<div align=center>
<img src="https://raw.githubusercontent.com/jhu-oose/2019-group-foodaholic/develop/pictures/Friends-Profile.png?token=AJEU26FOY5NAP5O3ZYSA7MC5QJIBI" 
    width="720" alt="friends_profile_page" />
</div> <br>


## User Stories

As an orginizer, I want to create a party for my birthday celebration. I first sign up and log in to my account in Foodaholic, then create a party by selecting the time and place, food list, and the party themes that I prefer. A invitation code of the party is generated, and I can send it to my friends to invite them to join in. 

As a user, I want to join a party of my friends. By viewing Foodaholic, I can browse the upcoming events nearby, find my friends' event and join them. I can come up with my ideas about the activities in the party. I can also view other's ideas and vote for them, and the final decision is upon the organizer. In the app, I can hat and comment on the party, and when the party ends, we shall split the bill.

# Viability

## Hardware

We are developing an Android application, so we have the hardware.

## APIs

All of these APIs are widely used, we should have access to them.

## Tools

We have access to Android Studio.

## Proof of Concept

It’s clear that our project is technically viable because beside tools in the toolbox, the APIs and other tools are also available.

# Difficulty

Our project we proposed is at the right difficulty scale because we would build an Android App and integrate the Google Maps API, Google Calendar API, Gmail API and Paypal API with our project. Combining these APIs would be difficult for us because it requires these applications to talk with each other, meanwhile, not conflict with other applications or functions. Also, these features we have proposed should be connected logically and interact with each other.

# Market Research

## Users

The users of this application would be people who need to attend some organized events like party and seminar. It's really convenient and helpful because this application provides a platform to allows people to get the real-time events information and search nearby events information online, join the events just by phone, select favorite food, chat with others, comment on the food and activities, and even organize or customize an event.

## Competition

Twitter is similar to our projet. Twitter allows people to know about the real-time events that they can join and track the event information. Different from the Twitter, our project helps people organize and join the event and order the food based on the food ranking and comments. There are many other features and functions such as chatting with others. It's not limited to knowing about the events information.

# Roadmap

https://github.com/jhu-oose/2019-group-foodaholic/projects

