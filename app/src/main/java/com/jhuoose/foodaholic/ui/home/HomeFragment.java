package com.jhuoose.foodaholic.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.ui.LoginActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    Button ChangePassword, logoutButton;
    ImageView profilePicture, editBioImage;
    TextView profileName;
    EditText profileBio;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f);
        ChangePassword = root.findViewById(R.id.btn_changepw);
        logoutButton = root.findViewById(R.id.btn_logout);
        profilePicture = root.findViewById(R.id.profilePic_im);
        editBioImage = root.findViewById(R.id.Edit_imageView);
        profileName = root.findViewById(R.id.profileName_tv);
        profileBio = root.findViewById(R.id.bio_textView);


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if LoginActivity.flag==0, u can login without input username and password.
                // if LoginActivity.flag == 1, u need to input your username and password to login.
                LoginActivity.flag = 1;
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}