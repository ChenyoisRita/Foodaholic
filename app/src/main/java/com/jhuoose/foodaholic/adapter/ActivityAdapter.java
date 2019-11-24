package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.viewmodel.ActivityProfile;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ActivityAdapter extends BaseAdapter {
    private List<ActivityProfile> mData;
    private Context mContext;

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

    //public long getPrice(int position) {return position};

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

        // Todo: this "price" is in String format for displaying properly. It should be changed to Int format.
        NumberFormat formatter = new DecimalFormat("0.00");
        holder.itemPrice.setText(formatter.format(currentActivity.getMoney()));


        holder.voteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: Call a method in ActivityController to add vote
//                mData.get(position).setVotes(mData.get(position).getVotes() + 1);
                currentActivity.setVote(currentActivity.getVote()+1);
                holder.activityVote.setText(Integer.toString(currentActivity.getVote()));
                holder.voteButton.setText("Voted");
                holder.voteButton.setEnabled(false);
                holder.booButton.setEnabled(false);
                notifyDataSetChanged();
            }
        });
        holder.booButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: Call a method in ActivityController to decrease vote
//                mData.get(position).setVotes(mData.get(position).getVotes() - 1);
                currentActivity.setVote(currentActivity.getVote()-1);
                holder.activityVote.setText(Integer.toString(currentActivity.getVote()));
                holder.booButton.setText("Booed");
                holder.voteButton.setEnabled(false);
                holder.booButton.setEnabled(false);
                notifyDataSetChanged();
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
