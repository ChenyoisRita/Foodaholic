package com.jhuoose.foodaholic.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    private String userEmail;
    public static int flag=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        heroku = HerokuService.getAPI();

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.registerButton);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = usernameEditText.getText().toString();
                loadingProgressBar.setVisibility(View.VISIBLE);
                signIn(usernameEditText.getText().toString().trim(),
                        passwordEditText.getText().toString().trim());
            }
        });
        
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void signIn(final String email, String password) {
//        Log.i("MyLog", "username: "+email+"; pwd "+password);
        Call<ResponseBody> call = heroku.login(email, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String welcome = getString(R.string.welcome) + email;
                    Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    showLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(LoginActivity.this, "Connection failed." +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        if (getFlag()==0){
//            FirebaseUser currentUser = mAuth.getCurrentUser();
//            updateUiWithUser(currentUser);
//        } else {
//            setFlag(0);
//        }
//
//    }

//    private void updateUiWithUser(FirebaseUser user) {
//        if (user != null) {
//            String welcome = getString(R.string.welcome) + user.getEmail();
//            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }
//    }

    private void showLoginFailed() {
        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
    }
}
