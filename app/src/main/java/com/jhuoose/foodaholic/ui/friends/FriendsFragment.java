package com.jhuoose.foodaholic.ui.friends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.adapter.FriendAdapter;
import com.jhuoose.foodaholic.api.HerokuAPI;
import com.jhuoose.foodaholic.api.HerokuService;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsFragment extends Fragment {
    private HerokuAPI heroku;
    private ListView friendListView;
    private FriendAdapter friendAdapter = null;
    public List<UserProfile> friendList = new ArrayList<>();
    Button searchFriendBtn, addFriendBtn;
    EditText searchFiend_Et;
    UserProfile tempFriend;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        heroku = HerokuService.getAPI();
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
                    intent.putExtra("friendName", tempFriend.getUserName());
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



        friendAdapter = new FriendAdapter(this.getActivity(), R.layout.item_friend, friendList);
        friendListView.setAdapter(friendAdapter);
        initFriendList();


        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
                intent.putExtra("friendID", String.valueOf(friendList.get(position).getId()));
                intent.putExtra("friendEmail", friendList.get(position).getEmail());
                intent.putExtra("friendName", friendList.get(position).getUserName());
                intent.putExtra("friendPhone", friendList.get(position).getPhone());
                startActivity(intent);
            }
        });

        return root;
    }

    public void initFriendList(){
        Call<List<UserProfile>> call_getFriendList = heroku.getFriendList();
        call_getFriendList.enqueue(new Callback<List<UserProfile>>() {
            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Fetch FriendList Error:"+response.errorBody(),Toast.LENGTH_SHORT).show();
                } else {
                    friendList.clear();
                    for (UserProfile userProfile: response.body()) {
                        friendList.add(userProfile);
                    }
                    friendAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                Toast.makeText(getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Boolean canFind(String friendName){
        for (UserProfile friend: friendList){
            if (friend.getUserName().equals(friendName)) {
                tempFriend = friend;
                return true;
            }
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        initFriendList();
    }

    public List<UserProfile> getFriendList() {
        return friendList;
    }

}