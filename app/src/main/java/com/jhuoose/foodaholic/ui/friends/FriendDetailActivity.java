package com.jhuoose.foodaholic.ui.friends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendDetailActivity extends AppCompatActivity {
    HerokuAPI heroku;
    TextView friendID_tv;
    EditText friendName_et, friendEmail_et, friendPhone_et;
    Button backBtn, deleteBtn;

    String friendID, friendEmail, friendName, friendPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        heroku = HerokuService.getAPI();

        friendID_tv = this.findViewById(R.id.friendID_real);
        friendName_et = this.findViewById(R.id.friendName_real);
        friendEmail_et = this.findViewById(R.id.friendEmail_real);
        friendPhone_et = this.findViewById(R.id.friendPhone_real);
        backBtn = this.findViewById(R.id.friendDetail_backBtn);
        deleteBtn = this.findViewById(R.id.friendDetail_deleteBtn);

        // Set uneditable
        friendName_et.setInputType(EditorInfo.TYPE_NULL);
        friendEmail_et.setInputType(EditorInfo.TYPE_NULL);
        friendPhone_et.setInputType(EditorInfo.TYPE_NULL);

        Intent intent = getIntent();
        friendID = intent.getStringExtra("friendID");
        friendEmail = intent.getStringExtra("friendEmail");
        friendName = intent.getStringExtra("friendName");
        friendPhone = intent.getStringExtra("friendPhone");

        friendID_tv.setText(friendID);
        friendName_et.setText(friendName);
        friendEmail_et.setText(friendEmail);
        friendPhone_et.setText(friendPhone);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendDetailActivity.this);
                builder.setMessage("Confirm to Delete "+friendName+" ?")
                        .setTitle("⚠️")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Call<ResponseBody> call_deleteFriend = heroku.deleteFriend(Integer.parseInt(friendID));
                                call_deleteFriend.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (!response.isSuccessful()) {
                                            Toast.makeText(FriendDetailActivity.this, "Delete Friend Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(FriendDetailActivity.this, "Delete "+friendName+" Successfully!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(FriendDetailActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                // Create Dialog Box
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
