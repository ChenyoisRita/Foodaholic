package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.BooerListAdapter;
import com.jhuoose.foodaholic.adapter.ParticipantListAdapter;
import com.jhuoose.foodaholic.adapter.VoterListAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.Activity;
import com.jhuoose.foodaholic.viewmodel.User;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    private int eid, activityID;

    private Button leaveActivity_btn, joinActivity_btn;
    private ListView votersListView, booersListView, participantsListView;

    private Activity currentActivity;
    private ArrayList<UserProfile> voteList, booList, participantList;
    public UserProfile currentUserProfile;
    public ParticipantListAdapter participantListAdapter;

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

        getCurrentUserProfile();

        joinActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                for(UserProfile tempUP: participantList){
                    if (tempUP.getId()==currentUserProfile.getId()){
                        Toast.makeText(getApplicationContext(), "You have Joined this!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Call<ResponseBody> call_joinActivity = heroku.joinActivity(activityID);
                call_joinActivity.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Join Activity Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Successfully!", Toast.LENGTH_SHORT).show();
                            participantList.add(currentUserProfile);
                            participantListAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Join Activity Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        leaveActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call_leaveActivity = heroku.leaveActivity(activityID);
                call_leaveActivity.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Leave Activity Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                        } else {
                            participantList.remove(currentUserProfile);
                            participantListAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Leave Activity Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
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
                    participantList = currentActivity.getParticipantList();

                    VoterListAdapter voterListAdapter = new VoterListAdapter(voteList, ActivityDetailActivity.this);
                    votersListView.setAdapter(voterListAdapter);

                    BooerListAdapter booerListAdapter = new BooerListAdapter(booList, ActivityDetailActivity.this);
                    booersListView.setAdapter(booerListAdapter);

                    participantListAdapter = new ParticipantListAdapter(participantList,ActivityDetailActivity.this);
                    participantsListView.setAdapter(participantListAdapter);

                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Get VoterBooerList Connection Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getCurrentUserProfile() {
        Call<UserProfile> call_getCurrentUserProfile = heroku.getCurrentUserProfile();
        call_getCurrentUserProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Get Userprofile Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                    User temp = EventsFragment.currentUser;
                    currentUserProfile.setId(temp.getId());
                    currentUserProfile.setEmail(temp.getEmail());
                    currentUserProfile.setPhone(temp.getPhone());
                    currentUserProfile.setUserName(temp.getUserName());
                } else {
                    currentUserProfile = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Get Userprofile Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
