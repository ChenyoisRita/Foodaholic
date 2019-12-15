package com.jhuoose.foodaholic.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.ui.events.ActivityDetailActivity;
import com.jhuoose.foodaholic.ui.events.EventDetailActivity;
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

            holder.itemPrice = convertView.findViewById(R.id.et_set_price);
            holder.activityTitle = convertView.findViewById(R.id.acticity_title);
            holder.voteButton = convertView.findViewById(R.id.vote_button);
            holder.activityVote = convertView.findViewById(R.id.activity_vote);
            holder.booButton = convertView.findViewById(R.id.boo_button);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity,parent,false);
        final ActivityProfile currentActivity = mData.get(position);

        holder.activityTitle.setText(currentActivity.getActivityName());
        holder.activityVote.setText(Integer.toString(currentActivity.getVote()));
        holder.itemPrice.setText(String.format("%.2f", currentActivity.getMoney()));

//        Todo: [HerokuAPI: Delete Activity Error]
        holder.activityTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("⚠️Delete Warning")
                        .setMessage("Confirm to Delete "+holder.activityTitle.getText().toString()+" ?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Call<ResponseBody> call_deleteActivity = herokuAPI.deleteActivity(EventDetailActivity.eid, currentActivity.getId());
                                call_deleteActivity.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (!response.isSuccessful()) {
                                            Log.i("DeleteActivity", "Delete Error:"+response.errorBody()+" ; eid:"+EventDetailActivity.eid);
                                            Toast.makeText(view.getContext(), "Delete Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(view.getContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(view.getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                // If return true, LongClick will not lead to the single click event.
                return true;
            }
        });


        holder.activityTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), ActivityDetailActivity.class);
                view.getContext().startActivity(intent1);
            }
        });

        NumberFormat formatter = new DecimalFormat("0.00");
        holder.itemPrice.setText(formatter.format(currentActivity.getMoney()));
        holder.itemPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.i("SetPrice", "CLick");
                final EditText inputPrice_et = new EditText(view.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Set Price💰").setView(inputPrice_et)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final float price = Float.valueOf(inputPrice_et.getText().toString().trim());
                                Log.i("SetPrice", "Price: "+price);
                                Call<ResponseBody> call_updateActivity = herokuAPI.updateActivityPrice(currentActivity.getId(), price);
                                call_updateActivity.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (!response.isSuccessful()){
                                            Toast.makeText(view.getContext(), "Setup Price Error:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            currentActivity.setMoney(price);
                                            holder.itemPrice.setText(String.valueOf(price));
                                            notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(view.getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                builder.show();
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



    class ViewHolder {
        TextView activityTitle;
        TextView itemPrice;
        Button voteButton;
        TextView activityVote;
        Button booButton;
    }
}
