package com.jhuoose.foodaholic.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    ImageView profilePic;
    TextView userID;
    EditText userName, userPhone, userEmail;
    Button confirmBtn,cancelBtn;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        heroku = HerokuService.getAPI();
        profilePic = findViewById(R.id.editProfilePic_im);
        userID = findViewById(R.id.userID_et);
        userName = findViewById(R.id.userName_et);
        userPhone = findViewById(R.id.userPhone_et);
        userEmail = findViewById(R.id.userEmail_et);
        confirmBtn = findViewById(R.id.editProfile_OK_btn);
        cancelBtn = findViewById(R.id.editProfile_cancel_btn);


        initializeFields();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canCreateProfile()) {
                    Call<ResponseBody> call_updateCurrentUserProfile = heroku.updateCurrentUserProfile(currentUser.getUserName(),
                            currentUser.getPhone(),currentUser.getEmail());
                    call_updateCurrentUserProfile.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(EditProfileActivity.this, "Update Profile Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditProfileActivity.this, "Update Profile Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(EditProfileActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
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
//      Todo: Update User profile image
        Call<User> call_getCurrentUser = heroku.getCurrentUser();
        call_getCurrentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(EditProfileActivity.this, "Fetch User Data Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    currentUser = response.body();
                    userID.setText(String.valueOf(currentUser.getId()));
                    userName.setText(currentUser.getUserName());
                    userPhone.setText(currentUser.getPhone());
                    userEmail.setText(currentUser.getEmail());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Conneciton Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean canCreateProfile() {
        if (userName.getText().equals("") || userName.getText()==null) {
            Toast.makeText(EditProfileActivity.this, "Invalid Name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (userPhone.getText().equals("") || userPhone.getText()==null) {
            Toast.makeText( EditProfileActivity.this, "Invalid Phone Type", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (userEmail.getText().equals("") || userEmail.getText()==null) {
            Toast.makeText( EditProfileActivity.this, "Invalid Email Type", Toast.LENGTH_SHORT).show();
            return false;
        }

        currentUser.setUserName(userName.getText().toString().trim());
        currentUser.setPhone(userPhone.getText().toString().trim());
        currentUser.setEmail(userEmail.getText().toString().trim());
        return true;
    }
}
