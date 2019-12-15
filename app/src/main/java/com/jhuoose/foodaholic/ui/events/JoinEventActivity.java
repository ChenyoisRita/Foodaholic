package com.jhuoose.foodaholic.ui.events;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinEventActivity extends AppCompatActivity {
    HerokuAPI heroku;
    Button joinEventBtn;
    EditText eventID_et;
    int eid;
    String entryCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);

        heroku = HerokuService.getAPI();

        joinEventBtn = findViewById(R.id.btn_search_event);
        eventID_et = findViewById(R.id.eventID_et);



        joinEventBtn.setOnClickListener(new View.OnClickListener() {
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

//    public void getInput(){
//        String eventID = eventID_et.getText().toString().trim();
//        if (eventID!=null && !eventID.equals("") && !eventID.equals(" ") && eventID.length()>0) {
//            eid = Integer.parseInt(eventID);
//        } else {
//            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }

    public boolean getValidEntryCode() {
        String code = eventID_et.getText().toString().trim();
        if (code!=null && !code.equals("") && !code.equals(" ") && code.length()>0) {
            entryCode = code;
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
