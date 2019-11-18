package com.jhuoose.foodaholic.ui.friends;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.model.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {
    private HerokuAPI heroku;
    private ListView friendListView;
    private FriendAdapter friendAdapter = null;
    public List<Friend> friendList = new ArrayList<>();
    Button searchFriendBtn, addFriendBtn;
    EditText searchFiend_Et;
    Friend tempFriend;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        friendListView = root.findViewById(R.id.friendListView);
        searchFriendBtn = root.findViewById(R.id.btn_search_friend);
        searchFiend_Et = root.findViewById(R.id.searchFriend_et);
        addFriendBtn = root.findViewById(R.id.btn_add_friend);

        searchFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_search = searchFiend_Et.getText().toString();
                if(canFind(name_search)){
                    Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
                    intent.putExtra("friendEmail", tempFriend.getEmail());
                    intent.putExtra("friendName", tempFriend.getName());
                    intent.putExtra("friendPhone", tempFriend.getPhone());
                    startActivity(intent);
                    tempFriend = null;
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Result");
                    alertDialog.setMessage("Find Nothing");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        });

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
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
                intent.putExtra("friendEmail", friendList.get(position).getEmail());
                intent.putExtra("friendName", friendList.get(position).getName());
                intent.putExtra("friendPhone", friendList.get(position).getPhone());
                startActivity(intent);
            }
        });

        return root;
    }

    public void initFriendList(){
        // Todo: get current User's friends from Server Database

        Log.i("myLog", "Before FOR");
        for (int i=0;i<5;i++){
            Friend temp = new Friend();
            temp.setName("temp"+i);
            temp.setEmail("temp"+i+"@gmail.com");
            temp.setPhone(""+i+i+i+i);
            friendList.add(temp);
        }
    }

    public Boolean canFind(String friendName){
        for (Friend friend: friendList){
            if (friend.getName().equals(friendName)) {
                tempFriend = friend;
                return true;
            }
        }
        return false;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

}