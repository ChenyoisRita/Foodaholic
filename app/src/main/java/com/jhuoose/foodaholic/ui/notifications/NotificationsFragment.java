package com.jhuoose.foodaholic.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.NotificationAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.model.Notification;
import com.jhuoose.foodaholic.viewmodel.EventProfile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {
    private HerokuAPI heroku;
    private ListView notificationListView;
    private NotificationAdapter notificationAdapter = null;

    public List<Notification> notificationList = new ArrayList<>();
    Notification tmpNotification;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationListView = root.findViewById(R.id.notificationListView);

        initNotificationList();

        notificationAdapter = new NotificationAdapter(this.getActivity(), R.layout.item_notification, notificationList);
        notificationListView.setAdapter(notificationAdapter);
        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), NotificationDetailActivity.class);
                intent.putExtra("eventTitle", notificationList.get(position).getEventTitle());
                intent.putExtra("notificationTitle", notificationList.get(position).getNotificationTitle());
                intent.putExtra("notificationContent", notificationList.get(position).getNotificationContent());
                startActivity(intent);
            }
        });


        return root;
    }
    public void initNotificationList(){
        for(int i=1; i<=2; i++){
            Notification temp = new Notification();
            temp.setNotificationTitle("Bill");
            temp.setNotificationContent("you should pay for" + i + "dollars");
            temp.setEventTitle("event" + i);
            notificationList.add(temp);
        }
        Notification temp = new Notification();
        temp.setNotificationTitle("invitation");
        temp.setNotificationContent("Your friend Bill Gates has sent you an invitation. \n Invitation code is HEWHYE");
        temp.setEventTitle("event invitation");
        notificationList.add(temp);

    }
    public List<Notification> getNotificationList() { return notificationList; }

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