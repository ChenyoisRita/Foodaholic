package com.jhuoose.foodaholic.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.ui.MainActivity;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    ImageView profilePic;
    EditText userName, userPhone;
    Button confirmBtn,cancelBtn;
    UserProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        heroku = HerokuService.getAPI();
        profilePic = findViewById(R.id.editProfilePic_im);
        userName = findViewById(R.id.userName_et);
        userPhone = findViewById(R.id.userPhone_et);
        confirmBtn = findViewById(R.id.editProfile_OK_btn);
        cancelBtn = findViewById(R.id.editProfile_cancel_btn);

        initializeFields();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canCreateProfile()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put(String.valueOf(profile.getId()), profile);
                    Call<UserProfile> call = heroku.updateCurrentUserProfile(map);
                    call.enqueue(new Callback<UserProfile>() {
                        @Override
                        public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(EditProfileActivity.this, "Update Profile Successfully", Toast.LENGTH_SHORT).show();
                                MainActivity.setCurrentUserProfile(profile);
                                finish();
                            } else {
                                Toast.makeText(EditProfileActivity.this, "Response Error: "+response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserProfile> call, Throwable t) {
                            Toast.makeText(EditProfileActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initializeFields() {
//      Todo: update User profile image
        profile = MainActivity.getCurrentUserProfile();
        userName.setText(profile.getUserName());
        userPhone.setText(profile.getPhone());
    }

    public boolean canCreateProfile() {
        if (userName.getText().equals("") || userName.getText()==null) {
            Toast.makeText(EditProfileActivity.this, "Invalid Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            profile.setUserName(userName.getText().toString().trim());
        }

        if (userPhone.getText().equals("") || userPhone.getText()==null) {
            Toast.makeText( EditProfileActivity.this, "Invalid Phone Type", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            profile.setPhone(userPhone.getText().toString().trim());
        }

        return true;
    }
}
