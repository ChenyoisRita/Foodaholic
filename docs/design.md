# Design

# Architecture

<!-- Is this a web application, a mobile application (React Native, iOS, Android?), a desktop application, and so forth? How do the different components (client, server, and so forth) communicate? Don’t simply list tools; tell a story. -->
## Client

### Login

- Properties
    - Sign Up
    - Log In
    - Forgot Password?

### My Events

- Properties
	- List of My Events
	- View Event Abstract
	- Enter My Event

### Event Search

- Properties
    - Event Search
        - Keyword
        - Map
    - List of Event
    - View Event Abstract
    - View Event Details
    - Request to Join in Event

### In Event

- Properties
    - List of Activity
    - Add Activity
    - Edit Activity
    - Vote Activity
    - Admin: Delete Activity
    - Admin: Edit bill
    - Admin: Publish bill
    - Invite 
    - Leave Event
    - Admin: Cancel Event
    - Set Notification
    - Admin: Send Notification
    - Admin: End Event

### Chats

- Categories
	- Notification
	- Event Message

### Friends

- Properties
	- List of Friends
		- IGetfriendList
	- Search Friends
		- ISearchID, ISearchName, ISearchEmail, ISearchPhone.
	- View Friend Profile
		- IViewProfile
	- Add Friends
		- IAddFriends

### Profile

- Properties
	- My Profile
	        - 
	- Edit Profile
		- IEditProfile, ISaveProfile, IExitProfile.
	- Log out
		-ILogOut, ISwitchAccount。
## Tools Outside the Toolbox

<!-- For each tool: What is it? Why did you choose it? Where do you get it? How do you learn it? Follow the model of how we presented the tools in the Toolbox. Cute original drawings encouraged. -->

# Class Diagram

![Class Diagram](<!-- TODO -->)
