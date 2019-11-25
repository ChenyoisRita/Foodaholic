package com.jhuoose.foodaholic.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.EventProfile;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    HerokuAPI herokuAPI = HerokuService.getAPI();
    public static UserProfile currentUserProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Current User Profile from Backend
        Call<UserProfile> callCurrentUserProfile = herokuAPI.getCurrentUserProfile();
        Log.i("HomeLog", "Seem get User Profile");
        callCurrentUserProfile.enqueue(new Callback<UserProfile>() {

            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    MainActivity.setCurrentUserProfile(response.body());
                    Log.i("MainLog", "Get CurUser Successful");
                }else{
                    Log.i("MainLog", "Get CurUser Unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                t.printStackTrace();
                Log.i("MainLog", "Connection Fail");
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_events, R.id.navigation_notifications,
                R.id.navigation_friends, R.id.navigation_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public static UserProfile getCurrentUserProfile() {
        return currentUserProfile;
    }

    public static void setCurrentUserProfile(UserProfile currentUserProfile) {
        MainActivity.currentUserProfile = currentUserProfile;
    }

}
