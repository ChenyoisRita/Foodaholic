package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.viewmodel.Notification;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification>{
    private int resourceId;
    List<Notification> notificationList;
    Context nContext;

    public NotificationAdapter(Context context, int textViewResourceId, List<Notification> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Notification notification = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView notificationItemView_title = view.findViewById(R.id.notification_title);
        TextView notificationItemView_content = view.findViewById(R.id.notification_content);
        notificationItemView_title.setText(notification.getCategory());
        notificationItemView_content.setText(notification.getContent());
        return view;
    }



}
