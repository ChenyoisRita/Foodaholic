package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jhuoose.foodaholic.model.Event;

import java.util.List;

import com.jhuoose.foodaholic.R;

public class EventListAdapter extends ArrayAdapter<Event> {
    private int layoutId;
    public EventListAdapter(Context context, int layoutId, List<Event> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        TextView nameView = (TextView) view.findViewById(R.id.event_name);
        TextView timeView = (TextView) view.findViewById(R.id.event_time);
        TextView placeView = (TextView) view.findViewById(R.id.event_place);

        nameView.setText(item.getDisplayName());
        timeView.setText(item.getTime());
        placeView.setText(item.getPlace());

        return view;
    }
}
