package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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
import com.jhuoose.foodaholic.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {
    private ListView eventListView;
    Button addEventButton;
    FirebaseDatabase database;

    private EventAdapter eventAdapter = null;

    private List<Event> eventList = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events,container,false);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);

        eventListView = root.findViewById(R.id.event_list);
        addEventButton = root.findViewById(R.id.btn_add_event);

        database = FirebaseDatabase.getInstance();
        eventList = new ArrayList<>();

        // Animation Start
        fadein.setDuration(1500);
        fadein.setFillAfter(true);
        addEventButton.startAnimation(fadein);
        // Animation End

        eventAdapter = new EventAdapter(eventList, getActivity());
        eventListView.setAdapter(eventAdapter);
        DatabaseReference myRef = database.getReference("Events");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Event newEvent = dataSnapshot.getValue(Event.class);
                eventList.add(newEvent);
                eventListView.setAdapter(eventAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                intent.putExtra("eid", eventList.get(position).getEid());
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