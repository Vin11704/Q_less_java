package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FoodDetailMainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Food> foodArrayList;
    private FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_detail_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init_data();
        adapter = new FoodAdapter(foodArrayList, this);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Set item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Get the clicked Food object
                Food selectedFood = foodArrayList.get(position);

                // Launch FoodDetail activity with selected food details
                Intent intent = new Intent(FoodDetailMainActivity.this, FoodDetailActivity.class);
                intent.putExtra("food_name", selectedFood.getFoodName());
                intent.putExtra("food_desc_long", selectedFood.getFoodDesc());
                intent.putExtra("food_image", selectedFood.getFoodImage());
                startActivity(intent);
            }
        });
    }

    private void init_data(){
        foodArrayList = new ArrayList<>();

        // Add food objects to the list
        Food food1 = new Food("Burger", "Store1_Burger", R.drawable.store1_burger);
        Food food2 = new Food("Ice-Cream", "Store2_Ice-Cream", R.drawable.store2_ice_cream);
        Food food3 = new Food("Rice-Noodle", "Store3_Rice-Noodle", R.drawable.store3_rice_noodle);
        Food food4 = new Food("Earth", "1", R.drawable.earth);
        Food food5 = new Food("Mars", "2", R.drawable.marss);
        Food food6 = new Food("Jupiter", "95", R.drawable.jupiter);
        Food food7 = new Food("Mercury", "0", R.drawable.mercury);
        Food food8 = new Food("Neptune", "14", R.drawable.neptune);
        Food food9 = new Food("Saturn", "146", R.drawable.saturn);
        Food food10 = new Food("Uranus", "27", R.drawable.uranus);
        Food food11 = new Food("Venus", "0", R.drawable.venus);


        foodArrayList.add(food1);
        foodArrayList.add(food2);
        foodArrayList.add(food3);
        foodArrayList.add(food4);
        foodArrayList.add(food5);
        foodArrayList.add(food6);
        foodArrayList.add(food7);
        foodArrayList.add(food8);
        foodArrayList.add(food9);
        foodArrayList.add(food10);
        foodArrayList.add(food11);
    }

}