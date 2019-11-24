package com.jhuoose.foodaholic.api;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        CookieHandler cookieHandler = new CookieManager();
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://foodaholic-server.herokuapp.com/")
//                .baseUrl("http://10.0.2.2:4000/") // for local test on android virtual machine
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.api = retrofit.create(HerokuAPI.class);
    }
}
