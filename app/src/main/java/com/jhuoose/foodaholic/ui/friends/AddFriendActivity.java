package com.jhuoose.foodaholic.ui.friends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.model.Friend;
import com.jhuoose.foodaholic.ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AddFriendActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    EditText friendEmail_et;
    Button cancelBtn, addFriendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        friendEmail_et = this.findViewById(R.id.addByemail_real);
        cancelBtn = this.findViewById(R.id.addFriend_cancel_btn);
        addFriendBtn = this.findViewById(R.id.addFriend_OK_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddFriendActivity.this, MainActivity.class));
//                Fragment friendsFragment = new FriendsFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, friendsFragment).commit();
            }
        });

        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = friendEmail_et.getText().toString();
                Friend friend = getFriend(email);
                if(friend!=null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddFriendActivity.this);
                    builder.setMessage("Name: "+friend.getName()+"\n Email: "+friend.getEmail())
                            .setTitle("Confirmation")
                            .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Todo: add this Friend to current user's friendList and update
                                    // the friendList of each other in the server database;

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
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(AddFriendActivity.this).create();
                    alertDialog.setTitle("Result");
                    alertDialog.setMessage("Find Nothing");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }

    public Friend getFriend(String email){
        /** Todo: Fetch user by the email from Server's database.
         *  Todo: If this user exists, the server should return it in the Friend format.
         *  Todo: If that user does not exist, return null.
        */
        return null;
    }
}
