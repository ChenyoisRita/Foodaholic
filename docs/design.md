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
	   	- IGetMyInfo
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
        - Phone
    - Methods (Implementation of Interfaces)
        - SignUp
        - EmailVerification
        - LogIn
        - GetSecurityQuestion
        - VerifySecurityQuestionAnswer
        - ResetPassword
        - SearchID
        - SearchName
        - SearchEmail
        - SearchPhone
        - AddFriends
        - EditProfile
        - SaveProfile
        - ExitProfile
        - LogOut
        - SwitchAccount
        
- UserView
    - Attributes
        - Name
        - Password
        - ID
        - Email
        - Address
        - Photo
        - Phone
    - Methods (Implementation of Interfaces) - CheckExistUser - ViewProfile - PublicView
        - GetFriendList
        - GetMyinfo

- UserController
    - Attributes
        - Name
        - Password
        - ID
        - Email
        - Address
        - Photo
        - Phone
    - Methods
        - Connection
        - getAll
        - CreateAccount
        - CancelAccount
        - UpdateProfile
        - UpdateFriendsList

- UserRepository
    - Attributes
        - Name
        - Password
        - ID
        - Email
        - Address
        - Photo
        - Phone
    - Methods
        - Connection
        - getAll
        - CreateAccount
        - CancelAccount
        - UpdateProfile
        - UpdateFriendsList

- Recommendation
    - Attributes
        - FriendsName
        - FriendsID
        - FriendsEmail
        - FriendsPhoto
        - FriendsPhone
    - Methods
        - GetFriendsInfo
        - DisplayRecomFriends
	
<!-- TODO for Mou -->

- Event
    - Attributes
        - EventName
        - Organizer
        - Location
        - Time 
        - Notes
        - Participants
        - Accessibility
        - Activity
    - Methods
        - ICreateEvent
        - IGetEventBasicInfo
        - IDeleteEvent
        - IGetEventDetailInfo
        
- EventView
    - Attributes 
        - EventName
        - Organizer
        - Location
        - Time 
        - Notes
        - Participants
        - Accessibility
        - Activity
    - Methods
        - IGetEventDetailInfo
        - IGetEventDetailInfo
        - IEventInfo
- EventRepository
    - Attributes 
        - Connection
    - Methods
        - IGetAllMyEventsInfo
        - ISearchByKeyword 
        - ISearchByEventName
        - ISearchByInvitationCode
        - IGetAllEvents
        - IRequestJoinEvent
- Email
    - Attributes 
        - ReceiverEmailAddress
    - Methods
        - IEventMessageSender
<!-- TODO for Haoran -->
- EventController
- Activity
- ActivityView
- ActivityController

<!-- TODO for Shuofeng -->
- Map
    - Attributes
        - GoogleMap (This depends on the Google Map API)
        - User (Users who request the map)
        - EventLocationList (The List which contains all the locations of events from the user who request the map)
    - Methods
        - IGetUser
        - IGetEventLocationList
        - ISetupMap (Initialize the map)
    
- Notification
    - Attributes
        - Sender
        - ReceiverList
        - Receiver
        - Content
        - SendTime
    - Methods
        - GetSender
        - GetReceiverList
        - GetReceiver
        - GetContent
        - GetSendTime
        - SendToReceiver
    
- Message
    - Attributes
        - Sender
        - Receiver
        - Content
        - SendTime
    - Methods
        - GetSender
        - GetReceiver
        - GetContent
        - GetSendTime
        - SendToReceiver
    
- Search
    - Attributes
        - SearchContent (The content in the user's search input field)
        - User (User who want to search an event)
        - SearchResult (The results that the server finds in its database)
    - Methods
        - GetSearchContent
        - GetUser (Get the user who want to search an event. After processing this request, the server can return the 
        search results to the corresponding user)
        - ProcessSearchRequest (Search the content on the database of the server.)
        - ReturnSearchResult (Return the search result to the user who request the search action)
    
- ...


## Tools Outside the Toolbox

<!-- For each tool: What is it? Why did you choose it? Where do you get it? How do you learn it? Follow the model of how we presented the tools in the Toolbox. Cute original drawings encouraged. -->
**1. Android Studio**
Android studio is the official tool for developing application exclusively for Android platform. It has a strong editor tool for developing creative UI and emulators for different versions to test and simulate sensors without having actual Android devices. 
We choose the Android Studio because we are going to develop an Android Application. The Android Studio could help a lot in accelerating our application development and building the highest-quality apps for each Android device. Also, it is based on IntelliJ IDEA, which is a familiar tool for us.
In order to get Android Studio, we just need to launch the Android Studio DMG file, drag and drop Android Studio into the Applications folder, then launch Android Studio.
To learn Android programming through Android Studio, we can follow the online tutorials or books. And then we should put what we learned into the practice in this project.

**2. JavaMail API**
JavaMail is a Java API used to send and receive email via SMTP, POP3 and IMAP.And now it is available for Android.
We choose the JavaMail API because JavaMail programming is based on Java programming language, which we are familiar with. Also, JavaMail API could provide us a platform-independent and protocol-independent framework to build mail and messaging framework. 
We can learn how to use JavaMail API through following the online tutorials and guidance. Then we should combine the knowledge with our project in practice.

**3. OpenLayers API**
OpenLayers is an open-source JavaScript library for displaying map data in web browsers as slippy maps. It provides an API for building rich web-based geographic applications similar to Google Maps and Bing Maps.




# Class Diagram

![Class Diagram](<!-- TODO -->)
