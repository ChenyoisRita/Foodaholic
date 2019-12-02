package com.jhuoose.foodaholic.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.ui.LoginActivity;
import com.jhuoose.foodaholic.ui.MainActivity;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private HerokuAPI heroku;
    Button ChangePassword, logoutButton;
    ImageView profilePicture, editBioImage;
    TextView profileName;
    public UserProfile profile = new UserProfile();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        heroku = HerokuService.getAPI();

        ChangePassword = root.findViewById(R.id.btn_changepw);
        logoutButton = root.findViewById(R.id.btn_logout);
        profilePicture = root.findViewById(R.id.profilePic_im);
        editBioImage = root.findViewById(R.id.Edit_imageView);
        profileName = root.findViewById(R.id.profileName_tv);

        editBioImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        Log.i("HomeLog", "Call back over");
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if LoginActivity.flag==0, u can login without input username and password.
                // if LoginActivity.flag == 1, u need to input your username and password to login.
                LoginActivity.flag = 1;
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        Call<UserProfile> callCurrentUserProfile = heroku.getCurrentUserProfile();
        callCurrentUserProfile.enqueue(new Callback<UserProfile>() {

            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    profile = response.body();
                    profileName.setText(response.body().getUserName());
                } else {
                    Toast.makeText(getContext(), "Fetch profile error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Connection failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}