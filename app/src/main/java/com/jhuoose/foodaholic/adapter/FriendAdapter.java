package com.jhuoose.foodaholic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.model.Friend;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FriendAdapter extends ArrayAdapter<Friend> {
    private int resourceId;
    List<Friend> friendList;
    Context mContext;

    public FriendAdapter(Context context, int textViewResourceId, List<Friend> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

//    @Override
//    public int getCount() {
//        return friendList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return friendList.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = LayoutInflater.from(mContext).inflate(R.layout.item_friend,viewGroup,false);
//        return null;
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Friend friend = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
        TextView friendItemView = view.findViewById(R.id.friend_Name);
        friendItemView.setText(friend.getName());
//        return super.getView(position, convertView, parent);
        return view;
    }
}
