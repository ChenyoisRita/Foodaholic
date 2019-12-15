package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;

public class ActivityDetailActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    private int eid;

    private Button leaveActivity_btn, joinActivity_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_act_detail);

        heroku = HerokuService.getAPI();
        joinActivity_btn = this.findViewById(R.id.join_activity_button);
        leaveActivity_btn = this.findViewById(R.id.leaveActivity_button);

        Intent intent = getIntent();
        eid = intent.getIntExtra("eventId", -1);



    }


}
