package com.jhuoose.foodaholic.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.NotificationAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.EventProfile;
import com.jhuoose.foodaholic.viewmodel.Notification;

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

    public List<Notification> notificationList;
    Notification tmpNotification;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        heroku = HerokuService.getAPI();

        notificationListView = root.findViewById(R.id.notificationListView);

        notificationList = new ArrayList<>();

        //initNotificationList();
        updateNotificationList();

        notificationAdapter = new NotificationAdapter(this.getActivity(), R.layout.item_notification, notificationList);
        notificationListView.setAdapter(notificationAdapter);
        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), NotificationDetailActivity.class);
                intent.putExtra("eventId", notificationList.get(position).getId());
                System.out.println("notificationList.get(position).getId())"+notificationList.get(position).getId());
                intent.putExtra("eventTitle", notificationList.get(position).getTitle());
                intent.putExtra("notificationTitle", notificationList.get(position).getCategory());
                intent.putExtra("notificationContent", notificationList.get(position).getContent());
                startActivity(intent);
                //updateNotificationList();
            }
        });


        return root;
    }


    public void onResume() {
        super.onResume();
        updateNotificationList();
    }

    public void initNotificationList(){
        for(int i=1; i<=2; i++){
            Notification temp = new Notification();
            temp.setId(i);
            temp.setCategory("Bill");
            temp.setContent("you should pay for" + i + "dollars");
            temp.setTitle("event" + i);
            notificationList.add(temp);
        }
        Notification temp = new Notification();
        temp.setId(3);
        temp.setCategory("invitation");
        temp.setContent("Your friend Bill Gates has sent you an invitation. Invitation code is HEWHYE");
        temp.setTitle("event invitation");
        notificationList.add(temp);

    }
    public List<Notification> getNotificationList() { return notificationList; }

    public void updateNotificationList() {
        Call<List<Notification>> CallNotification = heroku.getNotificationList();
        CallNotification.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Fetch event error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    notificationList.clear();
                    for (Notification notification: response.body()) {
                        notificationList.add(notification);
                    }
                    notificationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Log.i("GetCurrentUser", "Failure: Msg: "+t.getMessage()+";\n Cause"+t.getCause()
                        +";\n TackTrace: "+t.getStackTrace());

                Toast.makeText(getContext(), "Connection with NotificationList error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}