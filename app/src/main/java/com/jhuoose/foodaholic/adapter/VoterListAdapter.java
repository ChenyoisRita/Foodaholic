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

public class VoterListAdapter extends BaseAdapter {
    private ArrayList<UserProfile> mData;
    private Context mContext;

    public VoterListAdapter(ArrayList<UserProfile> mData, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_voters, null, true);

            holder.voterName = convertView.findViewById(R.id.voterName_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final UserProfile currentUserProfile = mData.get(position);

        holder.voterName.setText(currentUserProfile.getUserName());


        return convertView;
    }

    class ViewHolder {
        TextView voterName;
    }
}
