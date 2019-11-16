package com.jhuoose.foodaholic.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private HerokuAPI heroku;
//    private FirebaseAuth mAuth;
    private String userEmail;
    public static int flag=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        mAuth = FirebaseAuth.getInstance();
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

            }
        });

//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUiWithUser(user);
//                        } else {
//                            showLoginFailed();
//                        }
//                    }
//                });
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
