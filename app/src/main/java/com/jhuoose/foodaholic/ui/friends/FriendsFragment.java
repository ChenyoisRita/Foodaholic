package com.jhuoose.foodaholic.ui.friends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.FriendAdapter;
import com.jhuoose.foodaholic.model.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    private ListView friendListView;
    private FriendAdapter friendAdapter = null;
    public List<Friend> friendList = new ArrayList<>();
    Button searchFriendBtn, addFriendBtn;
    EditText searchFiend_Et;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        friendListView = root.findViewById(R.id.friendListView);
        searchFriendBtn = root.findViewById(R.id.btn_search_friend);
        searchFiend_Et = root.findViewById(R.id.searchFriend_et);
        addFriendBtn = root.findViewById(R.id.btn_add_friend);

        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddFriendActivity.class));
            }
        });

        initFriendList();

        friendAdapter = new FriendAdapter(this.getActivity(), R.layout.item_friend, friendList);
        friendListView.setAdapter(friendAdapter);
        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), FriendDetailActivity.class));
            }
        });


        return root;
    }

    public void initFriendList(){
        Log.i("myLog", "Before FOR");
        for (int i=0;i<5;i++){
            Friend temp = new Friend();
            temp.setName("temp"+i);
            temp.setEmail("temp"+i+"@gmail.com");
            temp.setPhone(""+i+i+i+i);
            friendList.add(temp);
        }
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

}