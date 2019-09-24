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
        - ISearchByKeyword, ISearchByEventName, ISearchByInvitationCode
    - List of Event
        - IGetAllEvents
    - View Event Abstract
        - IGetEventBasicInfo
    - View Event Details
        - IGetEventDetailInfo
    - Request to Join in Event
        - IRequestJoinEvent

### In Event

- Properties
	- Abstract
	- Description
	- Place
	- Time
	- List of Participants
	- List of Activities
- Features
    - Browse Activity
    	- IGetAllActivityAbstract
    - View Activity Details
    	- IGetActivityDetail
    - Add Activity
    	- ICreateActivity
    - Edit Activity
    	- IEditActivity, IGetActivityDetail
    - Vote Activity
    	- IVoteActivity, IDownVoteActivity
    - Admin: Delete Activity
    	- IDeleteActivity
    - Admin: Edit bill
    	- IEditActivity
    - Admin: Publish bill
    	- INotificationSender, IEventMessageSender
    - Invite
    	- INotificationSender
    - Accept Invitation
    	- ICreateParticipant, IEventMessageSender
    - Leave Event
    	- IDeleteParticipant, IEventMessageSender
    - Admin: Send Notification
    	- INotificationSender
    - Admin: Cancel/End Event
    	- IDeleteEvent, INotificationSender

### Chats

- Categories
	- Notification
	    - IBillNotify, IInviteNotify, IUpcomingEventNotify
	- Event Message
	    - IEventInfo : It will display an event on the "chats" page

### Friends

- Properties
	- List of Friends
		- IGetFriendList
	- Search Friends
		- ISearchID 
		- ISearchName
		- ISearchEmail
		- ISearchPhone
	- View Friend Profile
		- IViewFriendProfile
	- Add Friends
		- IAddFriends

### Profile

- Properties
	- My Profile
	   	- IGetMyinfo
	- Edit Profile
		- IEditProfile 
		- ISaveProfile 
		- IExitProfile
	- View Profile
		- IViewProfile
        	- IPublicView
	- Log out
		- ILogOut
		- ISwitchAccount

## Server

<!-- TODO for Amanda -->
- User
	- Attributes
		- Name
		- Password
		- ID
		- Email
		- Address
		- Photo
	- Methods (Implementation of Interfaces)
		- GetFriendList
		- SearchID
		- SearchName
		- SearchEmail
		- SearchPhone
		- AddFriends
		- GetMyinfo
		- EditProfile 
		- SaveProfile 
		- ExitProfile
		- LogOut
		- SwitchAccount
			
- UserView
	- Attributes
        	- 
	- Methods (Implementation of Interfaces)
        	- CheckExistUser
        	- ViewProfile
        	- PublicView
        	- ...
		
- UserController
	- Attributes
	- Methods
	
- UserRepository
	- Attributes
	- Methods
	
- Recommendation
	- Attributes
	- Methods
	

<!-- TODO for Mou -->

- Event
- EventView
- EventRepository
- Email

<!-- TODO for Haoran -->
- EventController
- Activity
- ActivityView
- ActivityController

<!-- TODO for Shuofeng -->
- Map
- Notification
- Message
- Search
- ...


## Tools Outside the Toolbox

<!-- For each tool: What is it? Why did you choose it? Where do you get it? How do you learn it? Follow the model of how we presented the tools in the Toolbox. Cute original drawings encouraged. -->

# Class Diagram

![Class Diagram](<!-- TODO -->)
