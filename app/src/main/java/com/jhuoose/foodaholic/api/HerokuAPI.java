package com.jhuoose.foodaholic.api;

import com.jhuoose.foodaholic.viewmodel.*;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface HerokuAPI {
    @FormUrlEncoded
    @POST("users")
    Call<ResponseBody> createUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseBody> login(@Field("email") String email, @Field("password") String password);

    @POST("users/logout")
    Call<ResponseBody> logout();

    @GET("users/current")
    Call<User> getCurrentUser();

    @DELETE("users/current")
    Call<ResponseBody> deleteCurrentUser();

    @GET("users/current/events")
    Call<List<EventProfile>> getParticipatingEventList();

    @POST("users/current/events/{eventId}")
    Call<ResponseBody> joinEvent(@Path("eventId") int eventId);

    @DELETE("users/current/events/{eventId}")
    Call<ResponseBody> leaveEvent(@Path("eventId") int eventId);

    //@GET("users/current/notifications")
    //Call<List<NotificationProfile>> getNotificationList();

    @GET("users/current/friends")
    Call<List<UserProfile>> getFriendList();

    @POST("users/current/friends/{friendId}")
    Call<ResponseBody> addFriend(@Path("friendId") int friendId);

    @DELETE("users/current/friends/{friendId}")
    Call<ResponseBody> deleteFriend(@Path("friendId") int friendId);

    @GET("users/current/profile")
    Call<UserProfile> getCurrentUserProfile();

    @FormUrlEncoded
    @PUT("users/current/profile")
//    Call<UserProfile> updateCurrentUserProfile(@FieldMap Map<String, Object> map);
    Call<ResponseBody> updateCurrentUserProfile(@Field("userName") String userName, @Field("phone") String phone,
                                               @Field("email") String email);


    @FormUrlEncoded
    @POST("events")
//    Call<ResponseBody> createEvent(@FieldMap Map<String, String> map);
    Call<ResponseBody> createEvent(@Field("eventName") String eventName, @Field("description") String description,
                                   @Field("location") String location, @Field("startTime") String startTime,
                                   @Field("endTime") String endTime, @Field("theme") String theme);

    @GET("events/{eventId}")
    Call<Event> getEvent(@Path("eventId") int eventId);

    @DELETE("events/{eventId}")
    Call<ResponseBody> deleteEvent(@Path("eventId") int eventId);

    @FormUrlEncoded
    @PUT("events/{eventId}")
    Call<ResponseBody> updateEvent(@Path("eventId") int eventId, @FieldMap Map<String, Object> map);

    @GET("events/{eventId}/activities")
    Call<List<ActivityProfile>> getActivityList(@Path("eventId") int eventId);

    @FormUrlEncoded
    @POST("events/{eventId}/activities")
//    Call<ResponseBody> createActivity(@Path("eventId") int eventId, @FieldMap Map<String, Object> map);
    Call<ResponseBody> createActivity(@Path("eventId") int eventId, @Field("activityName") String activityName,
                                      @Field("description") String description, @Field("vote") int vote,
                                      @Field("money") float money, @Field("category") String category);

    @POST("events/{eventId}/activities/{activityId}")
    Call<ResponseBody> deleteActivity(@Path("eventId") int eventId, @Path("activityId") int activityId, @FieldMap Map<String, Object> map);


    @GET("activities/{activityId}")
    Call<ResponseBody> getActivity(@Path("activityId") int activityId);

    @PUT("activities/{activityId}")
    Call<ResponseBody> updateActivity(@Path("activityId") int activityId, @Field("activityName") String activityName,
                                      @Field("money") float money, @Field("category") String category,
                                      @Field("description") String description);

    @PUT("activities/{activityId}/vote")
    Call<ResponseBody> vote(@Path("activityId") int activityId);

    @PUT("activities/{activityId}/boo")
    Call<ResponseBody> boo(@Path("activityId") int activityId);
}
