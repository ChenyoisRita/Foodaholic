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
import androidx.fragment.app.Fragment;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.EventAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.EventProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {
    private boolean shouldRefreshOnResume = false;
    private HerokuAPI heroku;
    private ListView eventListView;
    Button addEventButton;
    private EventAdapter eventAdapter = null;
    public List<EventProfile> eventList;
    public static List<EventProfile> participantingEventProfileList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events,container,false);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);
        heroku = HerokuService.getAPI();

        eventListView = root.findViewById(R.id.event_list);
        addEventButton = root.findViewById(R.id.btn_add_event);

        initializeEventListUI();

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddEventActivity.class));
                onStop();
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume) {
            Log.i("EventNumBug", "onResume num: "+this.getParticipantingEventProfileList().size());
            updateEventListUI();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

    public void initializeEventListUI() {
        // Get Current User's participating event list from Backend
        Call<List<EventProfile>> callEventProfile = heroku.getParticipatingEventList();
        callEventProfile.enqueue(new Callback<List<EventProfile>>() {
            @Override
            public void onResponse(Call<List<EventProfile>> call, Response<List<EventProfile>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Fetch event error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    for (EventProfile eventProfile: response.body()) {
                        participantingEventProfileList.add(eventProfile);

                        if (!getParticipantingEventProfileList().isEmpty()) {
                            Log.i("EventTest", "在create时获取event");
                            eventList = getParticipantingEventProfileList();
                            eventAdapter = new EventAdapter(eventList, getActivity());
                            eventListView.setAdapter(eventAdapter);


                            eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                                    Log.i("mylog", eventList.get(position).getEventName());
                                    intent.putExtra("eventId",eventList.get(position).getId());
                                    startActivity(intent);
                                    onStop();
                                }
                            });
                        } else {
                            // No participating event
                            Toast.makeText(getContext(), "No Joined Events Found", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<List<EventProfile>> call, Throwable t) {
                Toast.makeText(getContext(), "Connection with EventList error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateEventListUI() {
        if (!getParticipantingEventProfileList().isEmpty()) {
            if (eventList.size()!=getParticipantingEventProfileList().size()) {
                eventList = getParticipantingEventProfileList();
                eventAdapter.notifyDataSetChanged();
            }

//            eventAdapter = new EventAdapter(eventList, getActivity());
//            eventListView.setAdapter(eventAdapter);
//
//
//            eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(getActivity(), EventDetailActivity.class);
//                    intent.putExtra("eventId",eventList.get(position).getId());
//                    startActivity(intent);
//                    onStop();
//                }
//            });
        } else {
            // No participating event
            Toast.makeText(getContext(), "No Joined Events Found", Toast.LENGTH_SHORT).show();
        }
    }

    public static List<EventProfile> getParticipantingEventProfileList() {
        return participantingEventProfileList;
    }

    public static void setParticipantingEventProfileList(List<EventProfile> participantingEventProfileList) {
        EventsFragment.participantingEventProfileList = participantingEventProfileList;
    }
}