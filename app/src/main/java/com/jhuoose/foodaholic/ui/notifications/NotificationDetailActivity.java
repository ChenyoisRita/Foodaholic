package com.jhuoose.foodaholic.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.model.Notification;

import com.jhuoose.foodaholic.model.Notification;
import com.jhuoose.foodaholic.ui.events.JoinEventActivity;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationDetailActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    String eventTitle, notificationTitle, notificationContent, entryCode;
    TextView eventTitle_display, notificationTitle_display, notificationContent_display;
    Button acceptInvitationBtn, declineInvitationBtn;
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

        heroku = HerokuService.getAPI();
        acceptInvitationBtn = findViewById(R.id.acceptInvitationButton);
        declineInvitationBtn = findViewById(R.id.declineInvitationButton);

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

        acceptInvitationBtn..setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getValidEntryCode()) {
                    Call<ResponseBody> call_joinEvent = heroku.joinEvent(entryCode);
                    call_joinEvent.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Event Not Found:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(JoinEventActivity.this).create();
                                alertDialog.setTitle("Result");
                                alertDialog.setMessage("Join Event Successfully");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                });
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    public boolean getValidEntryCode(){
        //String code = eventID_et.getText().toString().trim();
        //String preInvitationCode = "Invitation code is ";
        String splitContent = notificationContent.split();
        String code = splitContent[splitContent.length() - 1];
        //String code = notificationContent.indexOf(preInvitationCode) + preInvitationCode.length()
        if (code!=null && !code.equals("") && !code.equals(" ") && code.length()>0) {
            entryCode = code;
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
