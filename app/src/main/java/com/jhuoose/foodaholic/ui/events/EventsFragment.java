package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jhuoose.foodaholic.MainActivity;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.EventListAdapter;
import com.jhuoose.foodaholic.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private EventsViewModel eventsViewModel;
    private List<Event> eventList = new ArrayList<Event>();
    private ListView eventListView;
    private EventListAdapter eventListAdapter;

    Button addEventButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        eventsViewModel =
//                ViewModelProviders.of(this).get(EventsViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_events, container, false);
//        final TextView textView = root.findViewById(R.id.text_events);
//        eventsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//
////        eventListView = (ListView)root.findViewById(R.id.event_list);
////        eventListAdapter = new EventListAdapter(MainActivity.class, R.layout.fragment_events, eventList)
//
//        return root;
        View v = inflater.inflate(R.layout.fragment_events,container,false);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);
        addEventButton = v.findViewById(R.id.btn_add_event);

        // Animation Start
        fadein.setDuration(1500);
        fadein.setFillAfter(true);
        addEventButton.startAnimation(fadein);
        // Animation End

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddEventActivity.class));
            }
        });

        return v;
    }

}