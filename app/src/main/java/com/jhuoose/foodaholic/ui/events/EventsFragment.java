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

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.EventAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.EventProfile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {
    private HerokuAPI heroku;
    private ListView eventListView;
    private Button addEventButton, joinEventBtn;
    private EventAdapter eventAdapter = null;
    public List<EventProfile> eventList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events,container,false);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);
        heroku = HerokuService.getAPI();

        eventListView = root.findViewById(R.id.event_list);
        addEventButton = root.findViewById(R.id.btn_add_event);
        joinEventBtn = root.findViewById(R.id.btn_join_event);

        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventList, getActivity());
        eventListView.setAdapter(eventAdapter);
        updateEventListUI();

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                Log.i("mylog", eventList.get(position).getEventName());
                intent.putExtra("eventId",eventList.get(position).getId());
                intent.putExtra("eventName", eventList.get(position).getEventName());
                startActivity(intent);
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddEventActivity.class));
            }
        });

        joinEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), JoinEventActivity.class));
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateEventListUI();
    }

    public void updateEventListUI() {
        Call<List<EventProfile>> callEventProfile = heroku.getParticipatingEventList();
        callEventProfile.enqueue(new Callback<List<EventProfile>>() {
            @Override
            public void onResponse(Call<List<EventProfile>> call, Response<List<EventProfile>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Fetch event error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    eventList.clear();
                    for (EventProfile eventProfile: response.body()) {
                        eventList.add(eventProfile);
                    }
                    eventAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<EventProfile>> call, Throwable t) {
                Toast.makeText(getContext(), "Connection with EventList error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}