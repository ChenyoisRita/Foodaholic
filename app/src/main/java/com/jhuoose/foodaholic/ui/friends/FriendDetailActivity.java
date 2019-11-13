package com.jhuoose.foodaholic.ui.friends;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;

import androidx.appcompat.app.AppCompatActivity;

public class FriendDetailActivity extends AppCompatActivity {
    EditText friendName_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        friendName_et = this.findViewById(R.id.friendName_real);
        // You cannot edit friend name
        friendName_et.setInputType(EditorInfo.TYPE_NULL);



    }
}
