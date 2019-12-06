package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.ActivityProfile;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAdapter extends BaseAdapter {
    private List<ActivityProfile> mData;
    private Context mContext;
    private HerokuAPI herokuAPI = HerokuService.getAPI();

    public ActivityAdapter(List<ActivityProfile> mData, Context mContext) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity,null,true);

            holder.itemPrice = (TextView) convertView.findViewById(R.id.et_set_price);
            holder.activityTitle = (TextView) convertView.findViewById(R.id.acticity_title);
            holder.voteButton = (Button) convertView.findViewById(R.id.vote_button);
            holder.activityVote = (TextView) convertView.findViewById(R.id.activity_vote);
            holder.booButton = (Button) convertView.findViewById(R.id.boo_button);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity,parent,false);
        final ActivityProfile currentActivity = mData.get(position);

        holder.activityTitle.setText(currentActivity.getActivityName());
        holder.activityVote.setText(Integer.toString(currentActivity.getVote()));

//        Todo: Set the price and Upload it to the server
        NumberFormat formatter = new DecimalFormat("0.00");
        holder.itemPrice.setText(formatter.format(currentActivity.getMoney()));
        holder.itemPrice.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_NEXT:
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        updateActivityPrice(currentActivity.getId(), Float.parseFloat(holder.itemPrice.getText().toString().trim()));
                        Log.i("ActivityAdapter:Price", currentActivity.getActivityName()+" Price: "+holder.itemPrice.getText().toString());
                        return true;
                }
                return false;
            }
        });

//        Todo: Now the vote and boo data can be uploaded to the Server. However, we need to ban the user to vote/boo if he has done that..
        holder.voteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                Call<ResponseBody> call_vote = herokuAPI.vote(currentActivity.getId());
                call_vote.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(v.getContext(), "Response Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                        } else {
                            currentActivity.setVote(currentActivity.getVote()+1);
                            holder.activityVote.setText(Integer.toString(currentActivity.getVote()));
                            holder.voteButton.setText("Voted");
                            holder.voteButton.setEnabled(false);
                            holder.booButton.setEnabled(false);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(v.getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        holder.booButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                Call<ResponseBody> call_boo = herokuAPI.boo(currentActivity.getId());
                call_boo.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(v.getContext(), "Response Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                        } else {
                            currentActivity.setVote(currentActivity.getVote()-1);
                            holder.activityVote.setText(Integer.toString(currentActivity.getVote()));
                            holder.booButton.setText("Booed");
                            holder.voteButton.setEnabled(false);
                            holder.booButton.setEnabled(false);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(v.getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        return convertView;
    }

//    Todo: Upload the activity price to the server.
    public void updateActivityPrice(int activityId, float price){

    }

    class ViewHolder {
        TextView activityTitle;
        TextView itemPrice;
        Button voteButton;
        TextView activityVote;
        Button booButton;
    }
}
