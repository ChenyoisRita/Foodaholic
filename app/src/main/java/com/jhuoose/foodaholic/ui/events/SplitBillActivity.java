package com.jhuoose.foodaholic.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplitBillActivity extends AppCompatActivity {
    private HerokuAPI heroku;
    private int eid;

    private Button splitBill_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill);

        heroku = HerokuService.getAPI();

        splitBill_btn = this.findViewById(R.id.submit_btn);

        Intent intent = getIntent();
        eid = intent.getIntExtra("eventId", -1);

        splitBill_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Call<ResponseBody> call_splitBill= heroku.splitBill(eid);
                call_splitBill.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Split Bill Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "The bill information has been sent Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });

    }
    }