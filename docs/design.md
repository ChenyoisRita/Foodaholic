# Design

# Architecture

<!-- Is this a web application, a mobile application (React Native, iOS, Android?), a desktop application, and so forth? How do the different components (client, server, and so forth) communicate? Don’t simply list tools; tell a story. -->
## Client

### Login

- Properties
    - Sign Up
        - ISignUp, IEmailVerification, ICheckExistUser
    - Log In
        - ILogIn
    - Forgot Password?
        - IGetSecurityQuestion, IVerifySecurityQuestionAnswer, IResetPassword

### My Events

- Properties
	- List of My Events
		- IGetAllMyEventsInfo
	- View Event Abstract
	- Enter My Event

### Event Search

- Properties
    - Event Search
        - I_SearchByKeyword, I_SearchByEventName, I_SearchByInvitationCode
    - List of Event
        - I_GetAllEvents
    - View Event Abstract
        - I_GetEventBasicInfo
    - View Event Details
        - I_GetEventDetailInfo
    - Request to Join in Event
        - I_RequestJoinEvent

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
	    - I_BillNotify, I_InviteNotify, I_UpcomingEventNotify
	- Event Message
	    - IEventInfo : It will display an event on the "chats" page

### Friends

- Properties
	- List of Friends
		- IGetFriendList
	- Search Friends
		- ISearchID, ISearchName, ISearchEmail, ISearchPhone.
	- View Friend Profile
		- IViewProfile
	- Add Friends
		- IAddFriends

### Profile

- Properties
	- My Profile
	    - IGetMyinfo
	- Edit Profile
		- IEditProfile, ISaveProfile, IExitProfile.
	- Log out
		- ILogOut, ISwitchAccount

## Server

- User
	- Attributes
		- Name
		- Password
	- Methods (Implementation of Interfaces)
		- ...
- UserView
	- Attributes
		- ...
	- Methods (Implementation of Interfaces)
		- ICheckExistUser
		- IProfileView
		- IPublicView
		- ...
- UserController
- Event
- EventView
- EventController
- Activity
- ActivityView
- ActivityController
- UserRepository
- EventRepository
- Email
- Map
- Notification
- Message
- Search
- Recommendation
- ...


## Tools Outside the Toolbox

<!-- For each tool: What is it? Why did you choose it? Where do you get it? How do you learn it? Follow the model of how we presented the tools in the Toolbox. Cute original drawings encouraged. -->

# Class Diagram

![Class Diagram](<!-- TODO -->)
