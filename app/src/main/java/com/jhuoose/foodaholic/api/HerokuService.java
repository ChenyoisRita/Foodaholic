package com.jhuoose.foodaholic.api;

import retrofit2.Retrofit;

public class HerokuService {
    private static final HerokuService ourInstance = new HerokuService();

    private HerokuAPI api;

    public static HerokuService getInstance() {
        return ourInstance;
    }

    public static HerokuAPI getAPI() {
        return ourInstance.api;
    }

    private HerokuService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://foodaholic-server.herokuapp.com/")
                .build();

        this.api = retrofit.create(HerokuAPI.class);
    }
}
