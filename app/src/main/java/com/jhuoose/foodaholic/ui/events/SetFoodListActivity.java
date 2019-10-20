package com.jhuoose.foodaholic.ui.events;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jhuoose.foodaholic.R;
import com.jhuoose.foodaholic.model.Food;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SetFoodListActivity extends AppCompatActivity {
    Button addFoodBtn;
    EditText foodNameEt;
    String foodName;
    LinearLayout foodListLayout;
    ArrayList<Food> foodList = AddEventActivity.foodList;
    private AlertDialog deleteFoodDialog;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        addFoodBtn = findViewById(R.id.btn_add_food);
        foodNameEt = findViewById(R.id.et_food_name);
        foodListLayout = findViewById(R.id.foodList_Layout);

        for (int i=0;i<foodList.size();i++) {
            Button existedFood = new Button(this);
            existedFood.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            existedFood.setText(foodList.get(i).getFoodName());
            existedFood.setId(i+1);
            foodListLayout.addView(existedFood);
        }

        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: U should check the input food name before add it.
                //Todo: If is null, notify user and do not add it.
                //Todo: Same food check.

                foodName = foodNameEt.getText().toString();
                final Button newFood = new Button(SetFoodListActivity.this);
                newFood.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                newFood.setText(foodName);
                newFood.setId(foodList.size()+1);

                //Todo: If touch the added food, you can edit it or delete it.
                newFood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                foodList.add(new Food(foodName));
                foodListLayout.addView(newFood);
                foodNameEt.setText("");
            }
        });
    }
}
