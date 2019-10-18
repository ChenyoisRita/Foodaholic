package com.jhuoose.foodaholic.ui.events;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.jhuoose.foodaholic.CurrentUser;
import com.jhuoose.foodaholic.MainActivity;
import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.ui.LoginActivity;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class AddEventActivity extends AppCompatActivity {
    Button cancel_btn,publish_Event_btn;
    TextView start_Time_tv, end_Time_tv,event_Date_tv;
    EditText event_Title_et, event_location_et, event_notes_et;
    String start_Time, end_Time, event_Date, event_Title, event_Location, event_notes;
    Event event;
    int currentYear,currentMonth, currentDay;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);

        event_Title_et = findViewById(R.id.event_title);
        event_location_et = findViewById(R.id.event_location);
        event_notes_et = findViewById(R.id.event_note);
        start_Time_tv = findViewById(R.id.start_time);
        end_Time_tv = findViewById(R.id.end_time);
        event_Date_tv = findViewById(R.id.event_date);
        cancel_btn = findViewById(R.id.btn_event_cancle);
        publish_Event_btn = findViewById(R.id.btn_event_publish);

        start_Time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment set_StartTime_Frag = new StartTimePickerFragment();
                set_StartTime_Frag.show(getSupportFragmentManager(), "StartTimePicker");
            }
        });

        end_Time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment set_EndTime_Frag = new EndTimePickerFragment();
                set_EndTime_Frag.show(getSupportFragmentManager(), "EndTimePicker");
            }
        });

        event_Date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Current Date
                final Calendar calendar = Calendar.getInstance();
                currentYear = calendar.get(Calendar.YEAR);
                currentMonth = calendar.get(Calendar.MONTH);
                currentDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dataPickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        event_Date_tv.setText((monthOfYear+1)+"/"+dayOfMonth+"/"+year);
                    }
                }, currentYear,currentMonth,currentDay);

                dataPickerDialog.show();

            }
        });

        publish_Event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateEvent();
                storeEventIntoFirebase();
                Toast.makeText(AddEventActivity.this, "store successful!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddEventActivity.this, MainActivity.class));
            }
        });


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddEventActivity.this, MainActivity.class));
            }
        });
    }

    public void storeEventIntoFirebase(){
        final ProgressDialog pd = new ProgressDialog(AddEventActivity.this);
        pd.setMessage("Uploading...");
        pd.show();
        // URL to Firebase user data json
        myRef = database.getInstance().getReference();
        myRef.child("Events").child(event.getEvent_Title()).setValue(event);
        pd.dismiss();
    }

    public void generateEvent(){
        start_Time = getText(start_Time_tv);
        end_Time = getText(end_Time_tv);
        event_Date = getText(event_Date_tv);
        event_Title = getText(event_Title_et);
        event_Location = getText(event_location_et);
        event_notes = getText(event_notes_et);
        event = new Event(start_Time, end_Time, event_Date, event_Title, event_Location, event_notes);
    }

    public String getText(TextView tv){
        return tv.getText().toString();
    }

    public String getText(EditText et){
        return et.getText().toString();
    }

}
