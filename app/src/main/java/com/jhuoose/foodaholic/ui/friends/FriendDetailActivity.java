package com.jhuoose.foodaholic.ui.friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.model.Friend;

import androidx.appcompat.app.AppCompatActivity;

public class FriendDetailActivity extends AppCompatActivity {
    EditText friendName_et, friendEmail_et;

    // friendEmail is used as FriendID.
    String friendEmail, friendName, friendPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        friendName_et = this.findViewById(R.id.friendName_real);
        friendEmail_et = this.findViewById(R.id.friendEmail_real);
        // You cannot edit friend name/email
        friendName_et.setInputType(EditorInfo.TYPE_NULL);
        friendEmail_et.setInputType(EditorInfo.TYPE_NULL);

        Intent intent = getIntent();
        friendEmail = intent.getStringExtra("friendEmail");
        friendName = intent.getStringExtra("friendName");
        friendPhone = intent.getStringExtra("friendPhone");

        friendName_et.setText(friendName);
        friendEmail_et.setText(friendEmail);

    }
}
