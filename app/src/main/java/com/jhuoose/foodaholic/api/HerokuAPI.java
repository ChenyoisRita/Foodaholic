package com.jhuoose.foodaholic.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface HerokuAPI {
    @FormUrlEncoded
    @POST("users")
    Call<ResponseBody> createUser(@Field("email") String email, @Field("password") String password);

    @DELETE("users/{userID}")
    Call<ResponseBody> deleteUser(@Path("userID") int userID);

    @FormUrlEncoded
    @PUT("users/{userID}")
    Call<ResponseBody> editUser(@Path("userID") int userID, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseBody> login(@Field("email") String email, @Field("password") String password);
}
