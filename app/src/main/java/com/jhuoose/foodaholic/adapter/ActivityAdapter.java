package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.model.Activity;

import java.util.List;

public class ActivityAdapter extends BaseAdapter {
    private List<Activity> mData;
    private Context mContext;
    TextView activityTitle;
    Button voteButton;
    TextView activityVote;
    Button booButton;

    public ActivityAdapter(List<Activity> mData, Context mContext) {
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity,parent,false);
        activityTitle = (TextView) convertView.findViewById(R.id.acticity_title);
        voteButton = (Button) convertView.findViewById(R.id.vote_button);
        activityVote = (TextView) convertView.findViewById(R.id.activity_vote);
        booButton = (Button) convertView.findViewById(R.id.boo_button);
        activityTitle.setText(mData.get(position).getTitle());
        activityVote.setText(Integer.toString(mData.get(position).getVotes()));
        voteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: Call a method in ActivityController to add vote
//                mData.get(position).setVotes(mData.get(position).getVotes() + 1);
                activityVote.setText(Integer.toString(mData.get(position).getVotes() + 1));
                voteButton.setText("Voted");
                voteButton.setEnabled(false);
                booButton.setEnabled(false);
            }
        });
        booButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: Call a method in ActivityController to decrease vote
//                mData.get(position).setVotes(mData.get(position).getVotes() - 1);
                activityVote.setText(Integer.toString(mData.get(position).getVotes() - 1));
                booButton.setText("Booed");
                voteButton.setEnabled(false);
                booButton.setEnabled(false);
            }
        });
        return convertView;
    }
}
