package com.jhuoose.foodaholic.ui.events;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.jhuoose.foodaholic.model.Event;
import com.jhuoose.foodaholic.model.Food;
import com.jhuoose.foodaholic.ui.MainActivity;
import com.jhuoose.foodaholic.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

//Todo: before Publish a new event, we should send an invitation email to all the attendees.
//Todo: This EMail should contain the event's number so that they can search this event and join it.
public class AddEventActivity extends AppCompatActivity {
    public static ArrayList<Food> foodList = new ArrayList<>();
    ArrayList<String> attendeeList = new ArrayList<>();

    Button cancelBtn, publishEventBtn, foodListBtn, eventThemeBtn, addAttendeeBtn;
    TextView startTimeTv, endTimeTv, eventDateTv;
    EditText eventTitleEt, eventLocationEt, eventNotesEt, attendeeEt;
    String startTime, endTime, eventDate, eventTitle, eventLocation, eventNotes;
    String selectedEventTheme, newAttendeeEmail;
    Event event;
    LinearLayout attendeeListLayout;
    // ListView attendeeListLayout;
    int currentYear,currentMonth, currentDay;
    private AlertDialog eventThemeDialog, deleteAttendeeDialog;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);

        database = FirebaseDatabase.getInstance();

        eventTitleEt = findViewById(R.id.event_title);
        eventLocationEt = findViewById(R.id.event_location);
        eventNotesEt = findViewById(R.id.event_note);
        startTimeTv = findViewById(R.id.start_time);
        endTimeTv = findViewById(R.id.end_time);
        eventDateTv = findViewById(R.id.event_date);
        cancelBtn = findViewById(R.id.btn_event_cancle);
        publishEventBtn = findViewById(R.id.btn_event_publish);
        foodListBtn = findViewById(R.id.btn_food_list);
        eventThemeBtn = findViewById(R.id.btn_event_theme);
        attendeeEt = findViewById(R.id.et_email_address);
        addAttendeeBtn = findViewById(R.id.btn_add_attendee);
        //attendeeListLayout = findViewById(R.id.list_attendee_layout);
        attendeeListLayout = findViewById(R.id.list_attendee_layout);

        startTimeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment set_StartTime_Frag = new StartTimePickerDialog();
                set_StartTime_Frag.show(getSupportFragmentManager(), "StartTimePicker");
            }
        });

        endTimeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment set_EndTime_Frag = new EndTimePickerDialog();
                set_EndTime_Frag.show(getSupportFragmentManager(), "EndTimePicker");
            }
        });

        eventDateTv.setOnClickListener(new View.OnClickListener() {
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
                        eventDateTv.setText((monthOfYear+1)+"/"+dayOfMonth+"/"+year);
                    }
                }, currentYear,currentMonth,currentDay);

                dataPickerDialog.show();

            }
        });

        foodListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddEventActivity.this, SetFoodListActivity.class));
            }
        });

        eventThemeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddEventActivity.this);
                builder.setTitle("Set the theme of this event");
                final String[] themeList = new String[]{
                        "Birthday Party",
                        "Seminar",
                        "Retirement Party",
                        "Bachelor Party",
                        "Ice Breaking Party"
                };

                int checkedItem = -2;
                if (selectedEventTheme==null||selectedEventTheme.equals("")){
                    checkedItem = -1;
                }else{
                    for (int i=0;i<themeList.length;i++){
                        if (themeList[i].equals(selectedEventTheme)){
                            checkedItem = i;
                            break;
                        }else{
                            checkedItem = -1;
                        }
                    }
                }

                builder.setSingleChoiceItems(themeList, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Get the text of the selected item in this Alert Dialog
                        selectedEventTheme = Arrays.asList(themeList).get(i);
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eventThemeDialog.dismiss();
                        Toast.makeText(AddEventActivity.this, "Theme: "+selectedEventTheme, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eventThemeDialog.dismiss();
                    }
                });

                eventThemeDialog = builder.create();
                eventThemeDialog.show();
            }
        });

        addAttendeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAttendeeEmail = attendeeEt.getText().toString().trim();
                if (newAttendeeEmail ==null|| newAttendeeEmail.equals("")){
                    Toast.makeText(AddEventActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    return;
                }

                final TextView newAttendee = new TextView(AddEventActivity.this);
                newAttendee.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                newAttendee.setText(newAttendeeEmail);
                newAttendee.setId(attendeeList.size()+1);

                newAttendee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(AddEventActivity.this);
                        builder.setTitle("Delte "+newAttendee.getText().toString().trim()+" ?");
                        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteAttendeeDialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (int j=0;j<attendeeList.size();j++){
                                    if (attendeeList.get(j).equals(newAttendeeEmail)){
                                        attendeeList.remove(j);
                                        break;
                                    }
                                }
                                attendeeListLayout.removeView(newAttendee);
                                deleteAttendeeDialog.dismiss();
                                Toast.makeText(AddEventActivity.this, newAttendeeEmail+" is Deleted",Toast.LENGTH_SHORT).show();
                            }
                        });

                        deleteAttendeeDialog = builder.create();
                        deleteAttendeeDialog.show();
                    }
                });

                attendeeList.add(newAttendeeEmail);
                // newAttendee.setBackgroundColor(Color.GRAY);
                // Log.i("MyLog", newAttendeeEmail+"; "+newAttendee.getText().toString());
                attendeeListLayout.addView(newAttendee);
                attendeeEt.setText("");

            }
        });

        publishEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (generateEvent()){
                  storeEventIntoFirebase();
                  Toast.makeText(AddEventActivity.this, "Publish Successful!", Toast.LENGTH_LONG).show();
                  startActivity(new Intent(AddEventActivity.this, MainActivity.class));
              }
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
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
        DatabaseReference myRef = database.getReference();
        myRef.child("Events").child(event.getEvent_Title()).setValue(event);
        pd.dismiss();
    }

    public Boolean generateEvent(){
        startTime = getText(startTimeTv);
        endTime = getText(endTimeTv);
        eventDate = getText(eventDateTv);
        eventTitle = getText(eventTitleEt);
        eventLocation = getText(eventLocationEt);
        eventNotes = getText(eventNotesEt);
        final Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);
        nowMonth = nowMonth+1;
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);

        if (startTime.equals("Please choose time")|| endTime.equals("Please choose time") || eventDate.equals("Please choose the date of Event")
                || eventTitle == null || eventLocation == null || eventNotes == null){
            Toast.makeText(getApplicationContext(),"Info cannot be null",Toast.LENGTH_LONG).show();
            return false;
        }

        String[] strarray = eventDate.split("\\/");
        int getMonth = Integer.parseInt(strarray[0]);
        int getDay = Integer.parseInt(strarray[1]);
        int getYear = Integer.parseInt(strarray[2]);

        String[] strarray_start = startTime.split("\\:");
        int getHour_st = Integer.parseInt(strarray_start[0]);
        int getMinute_st = Integer.parseInt(strarray_start[1]);

        String[] strarray_end = endTime.split("\\:");
        int getHour_end = Integer.parseInt(strarray_end[0]);
        int getMinute_end = Integer.parseInt(strarray_end[1]);

        //Judge the time is valid or not
        if(getYear < nowYear) {
            Toast.makeText(getApplicationContext(),"Invalid Year",Toast.LENGTH_LONG).show();
            return false;
        } else if(getYear == nowYear){
            if(getMonth < nowMonth) {
                Toast.makeText(getApplicationContext(),"Invalid Month!",Toast.LENGTH_LONG).show();
                return false;
            } else if(getMonth == nowMonth){
                if(getDay <= nowDay) {
                    Toast.makeText(getApplicationContext(),"Invalid Day!",Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        }

        if(getHour_st > getHour_end) {
            Toast.makeText(getApplicationContext(),"Invalid Hour!",Toast.LENGTH_LONG).show();
            return false;
        } else if(getHour_st == getHour_end){
            if(getMinute_st >= getMinute_end) {
                Toast.makeText(getApplicationContext(),"Invalid Minute!",Toast.LENGTH_LONG).show();
                return false;
            }
        }

        if (selectedEventTheme==null||selectedEventTheme.equals("")){
            Toast.makeText(getApplicationContext(), "Please set theme", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (foodList.size()==0){
            Toast.makeText(getApplicationContext(), "Please set food", Toast.LENGTH_SHORT).show();
            return false;
        }

        event = new Event(startTime, endTime, eventDate, eventTitle, eventLocation, eventNotes, selectedEventTheme, foodList, attendeeList);
        return true;
}

    public String getText(TextView tv){
        return tv.getText().toString();
    }

    public String getText(EditText et){
        return et.getText().toString();
    }

}
