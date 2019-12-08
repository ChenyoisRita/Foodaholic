package com.jhuoose.foodaholic.ui.friends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFriendActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    EditText friendID_et;
    Button cancelBtn, addFriendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        heroku = HerokuService.getAPI();

        friendID_et = this.findViewById(R.id.addByID_real);
        cancelBtn = this.findViewById(R.id.addFriend_cancel_btn);
        addFriendBtn = this.findViewById(R.id.addFriend_OK_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddFriendActivity.this, MainActivity.class));
                finish();
//                Fragment friendsFragment = new FriendsFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, friendsFragment).commit();
            }
        });

        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempID = friendID_et.getText().toString().trim();
                int inputID;
                if (tempID!=null && !tempID.equals("") && Integer.parseInt(tempID)>=0){
                    inputID = Integer.parseInt(tempID);

                    Call<ResponseBody> call_addFriend = heroku.addFriend(inputID);
                    call_addFriend.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(AddFriendActivity.this, "Add Friend Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(AddFriendActivity.this).create();
                                alertDialog.setTitle("Result");
                                alertDialog.setMessage("Add Friend Successfully");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                });
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(AddFriendActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(AddFriendActivity.this, "Invalid input ID type", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
