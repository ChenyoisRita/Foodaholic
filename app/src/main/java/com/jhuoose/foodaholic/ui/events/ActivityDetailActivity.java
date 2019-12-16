package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.BooerListAdapter;
import com.jhuoose.foodaholic.adapter.VoterListAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.Activity;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    private int eid, activityID;

    private Button leaveActivity_btn, joinActivity_btn;
    private ListView votersListView, booersListView, participantsListView;

    private Activity currentActivity;
    private ArrayList<UserProfile> voteList, booList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_act_detail);

        heroku = HerokuService.getAPI();
        joinActivity_btn = this.findViewById(R.id.join_activity_button);
        leaveActivity_btn = this.findViewById(R.id.leaveActivity_button);
        votersListView = findViewById(R.id.activity_voter_list);
        booersListView = findViewById(R.id.activity_booer_list);
        participantsListView = findViewById(R.id.activity_participant_list);

        Intent intent = getIntent();
        eid = intent.getIntExtra("eventID", -1);
        activityID = intent.getIntExtra("activityID", -1);

//        Log.i("ActivitiDetail", "eid: "+eid+"; aID:"+activityID);

        updateVoterABooerList(eid, activityID);

        joinActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        leaveActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void updateVoterABooerList(int eid, int activityID) {
        Call<Activity> call_getActivity = heroku.getActivity(activityID);
        call_getActivity.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Get VoteBoo List Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    currentActivity = response.body();
                    voteList = currentActivity.getVoteList();
                    booList = currentActivity.getBooList();

                    VoterListAdapter voterListAdapter = new VoterListAdapter(voteList, ActivityDetailActivity.this);
                    votersListView.setAdapter(voterListAdapter);

                    BooerListAdapter booerListAdapter = new BooerListAdapter(booList, ActivityDetailActivity.this);
                    booersListView.setAdapter(booerListAdapter);

                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Get VoterBooerList Connection Error", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
