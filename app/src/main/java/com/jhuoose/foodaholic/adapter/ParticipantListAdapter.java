package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import java.util.ArrayList;

public class ParticipantListAdapter extends BaseAdapter {
    private ArrayList<UserProfile> mData;
    private Context mContext;

    public ParticipantListAdapter(ArrayList<UserProfile> mData, Context mContext) {
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_participants, null,true);
            holder.participantName = convertView.findViewById(R.id.participantName_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UserProfile currentUserProfile = mData.get(position);

        holder.participantName.setText(currentUserProfile.getUserName());

        return convertView;
    }

    class ViewHolder {
        TextView participantName;
    }
}
