package com.jhuoose.foodaholic.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.ui.LoginActivity;
import com.jhuoose.foodaholic.ui.MainActivity;
import com.jhuoose.foodaholic.viewmodel.UserProfile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    Button ChangePassword, logoutButton;
    ImageView profilePicture, editBioImage;
    TextView profileName;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        ChangePassword = root.findViewById(R.id.btn_changepw);
        logoutButton = root.findViewById(R.id.btn_logout);
        profilePicture = root.findViewById(R.id.profilePic_im);
        editBioImage = root.findViewById(R.id.Edit_imageView);
        profileName = root.findViewById(R.id.profileName_tv);

        profileName.setText(MainActivity.getCurrentUserProfile().getUserName());

        editBioImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                onStop();
            }
        });

        Log.i("HomeLog", "Call back over");
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if LoginActivity.flag==0, u can login without input username and password.
                // if LoginActivity.flag == 1, u need to input your username and password to login.
                LoginActivity.flag = 1;
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        profileName.setText(MainActivity.getCurrentUserProfile().getUserName());
    }
}