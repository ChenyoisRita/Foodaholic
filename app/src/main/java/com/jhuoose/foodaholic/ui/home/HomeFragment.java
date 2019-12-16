package com.jhuoose.foodaholic.ui.home;

import android.content.Intent;
import android.os.Bundle;
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
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private HerokuAPI heroku;
    Button logoutButton;
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

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call_logout = heroku.logout();
                call_logout.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getContext(), "Logout Fail:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
//                            getActivity().getSupportFragmentManager().popBackStack();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
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