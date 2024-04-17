package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.q_less_java.Adapter.CategoryAdaptor;
import com.example.q_less_java.Domain.CategoryDomain;

import java.util.ArrayList;

public class restaurant3 extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;

    ImageView store3_arrow;
    CardView viewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant3);

        recyclerViewCategoryList();

        store3_arrow = findViewById(R.id.store3_arrow);
        viewCard = findViewById(R.id.viewCart3);

        // Making the items clickable and link to sub-pages
        store3_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant3.this, restaurant_list.class);
                startActivity(intent);
            }
        });

        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant3.this, CartListActivity.class);
                startActivity(intent);
            }
        });


    }

    private void recyclerViewCategoryList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView3);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);


        // This will be the list of all the items that we want to display in the RecyclerView, in which CategoryDomain is the function to create new displaying object with specific attributes/files
        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Signature Beef Noodle", "store3_menu1", "Savor the warmth and flavor of our Signature Beef Noodle Soup. Tender beef, hearty vegetables, and...", 10.50));
        category.add(new CategoryDomain("Mixed Seafood Rice Noodle", "store3_menu2","Each spoonful is a symphony of flavors, warming your soul with every slurp. A taste of seaside...", 7.50));
        category.add(new CategoryDomain("Duck Noodle Soup", "store3_menu3", "Savor the warmth and flavor of our Duck Noodle Soup, featuring succulent roasted duck slices... ", 7.00));
        category.add(new CategoryDomain("Chicken Noodle", "store3_menu4", "Savor the comforting warmth of our Chicken Noodle Soup. Tender chicken, slow-cooked overnight...", 7.20));
        category.add(new CategoryDomain("Jumbo Meat Lover Bowl", "store3_menu5", "Savor the ultimate carnivore's delight with our Jumbo Meat Lover Bowl! Overflowing with premium meats...", 10.50));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}