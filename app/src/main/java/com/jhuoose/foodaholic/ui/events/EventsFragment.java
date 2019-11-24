package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.EventAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.Event;
import com.jhuoose.foodaholic.viewmodel.EventProfile;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {
    private HerokuAPI heroku;
    private ListView eventListView;
    Button addEventButton;
   // FirebaseDatabase database;

    private EventAdapter eventAdapter = null;

    public static List<EventProfile> eventList = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events,container,false);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);
        heroku = HerokuService.getAPI();

        eventListView = root.findViewById(R.id.event_list);
        addEventButton = root.findViewById(R.id.btn_add_event);

       // database = FirebaseDatabase.getInstance();
        eventList = new ArrayList<>();

        // Animation Start
        fadein.setDuration(1500);
        fadein.setFillAfter(true);
        addEventButton.startAnimation(fadein);
        // Animation End


        Call<List<EventProfile>> call = heroku.getParticipatingEventList();
        call.enqueue(new Callback<List<EventProfile>>() {
            @Override
            public void onResponse(Call<List<EventProfile>> call, Response<List<EventProfile>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Display event failed" + response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    for (EventProfile eventProfile: response.body()) {
                        eventList.add(eventProfile);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<EventProfile>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Connection failed." +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        eventAdapter = new EventAdapter(eventList, getActivity());
        eventListView.setAdapter(eventAdapter);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                Log.i("mylog", eventList.get(position).getEventName());
                intent.putExtra("eventId",eventList.get(position).getId());
                startActivity(intent);
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddEventActivity.class));
            }
        });

        return root;
    }

}