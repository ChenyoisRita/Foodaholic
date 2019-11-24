package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.viewmodel.EventProfile;

import java.util.List;

public class EventAdapter extends BaseAdapter {
    private List<EventProfile> mData;
    private Context mContext;

    public EventAdapter(List<EventProfile> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_event,parent,false);
        TextView eventName = (TextView) convertView.findViewById(R.id.event_name);
        TextView eventTime = (TextView) convertView.findViewById(R.id.event_time);
        TextView eventPlace = (TextView) convertView.findViewById(R.id.event_place);
        eventName.setText(mData.get(position).getEventName());
        eventTime.setText(mData.get(position).getStartTime() + " - "
                            + mData.get(position).getEndTime());
        eventPlace.setText(mData.get(position).getLocation());
        return convertView;
    }
}
