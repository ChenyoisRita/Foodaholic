package com.jhuoose.foodaholic.ui.notifications;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.model.Notification;

import com.jhuoose.foodaholic.model.Notification;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationDetailActivity extends AppCompatActivity {
    String eventTitle, notificationTitle, notificationContent;
    TextView eventTitle_display, notificationTitle_display, notificationContent_display;
    Notification notification;

//    @override
//    protected void onCreate(Bundle savedInstanceState){
//
//    }
//

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        eventTitle_display = this.findViewById(R.id.eventTitle_display_tv);
        notificationTitle_display = this.findViewById(R.id.notificationTitle_display_tv);
        notificationContent_display = this.findViewById(R.id.notificationContent_display_tv);

        Intent intent = getIntent();
        eventTitle = intent.getStringExtra("eventTitle");
        notificationTitle = intent.getStringExtra("notificationTitle");
        notificationContent = intent.getStringExtra("notificationContent");

        eventTitle_display.setText(eventTitle);
        notificationTitle_display.setText(notificationTitle);
        notificationContent_display.setText(notificationContent);

    }
}
