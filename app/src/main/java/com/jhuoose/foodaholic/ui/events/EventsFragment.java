package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private EventsViewModel eventsViewModel;
    private ListView eventListView;

    Button addEventButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events,container,false);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);
        addEventButton = root.findViewById(R.id.btn_add_event);

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

        return root;
    }

}