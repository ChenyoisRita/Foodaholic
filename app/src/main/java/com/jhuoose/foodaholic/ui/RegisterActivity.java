package com.jhuoose.foodaholic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    Button btnRegister,btnCancel;
    EditText emailTxt,password,password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        heroku = HerokuService.getAPI();
        btnRegister = findViewById(R.id.ConfirmRegister);
        btnCancel = (Button) findViewById(R.id.cancelRegister);
        emailTxt = (EditText) findViewById(R.id.register_email);
        password = (EditText)findViewById(R.id.register_password);
        password_confirm = (EditText)findViewById(R.id.register_password_confirm);;


        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userEmail = emailTxt.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
                String userPassword_confirm = password_confirm.getText().toString().trim();

                if (TextUtils.isEmpty(userEmail)){
                    emailTxt.setError("PLZ input valid email address");
                    return;
                }

                if (userPassword.length()<6){
                    password.setError("Password should be longer than 6 characters");
                    return;
                }

                if(!userPassword.equals(userPassword_confirm)) {
                    password_confirm.setError("PLZ input the same password");
                    return;
                }

                Call<ResponseBody> call = heroku.createUser(userEmail, userPassword);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }else{
//                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "Connection failed." +t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
