package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.q_less_java.Adapter.CategoryAdaptor;
import com.example.q_less_java.Domain.CategoryDomain;

import java.util.ArrayList;

public class restaurant5 extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;


    ImageView store5_arrow;

    CardView viewCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant5);

        recyclerViewCategoryList();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Import clickable items
        store5_arrow = findViewById(R.id.store5_arrow);
        viewCard = findViewById(R.id.viewCart);

        // Making the items clickable and link to sub-pages
        store5_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant5.this, restaurant_list.class);
                startActivity(intent);
            }
        });


        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant5.this, CartListActivity.class);
                startActivity(intent);
            }
        });
    }


    // Initiate recyclerview feature
    private void recyclerViewCategoryList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.charaRecyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);


        // This will be the list of all the items that we want to display in the RecyclerView, in which CategoryDomain is the function to create new displaying object with specific attributes/files
        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pad Kra Pao", "store5_menu1", "Thai basil mice pork rice topped with fried egg giving both pleasant look and fragrance", 6.30));
        category.add(new CategoryDomain("Tom Yum Talay", "store5_menu2","Fresh sea food cooked in authentic tom yom paste.", 8.80));
        category.add(new CategoryDomain("Super Mixed Salad", "store5_menu3", "Flavourful papaya salad generously filled with fresh ingredients.", 16.90));
        category.add(new CategoryDomain("Chicken Green Curry Soup", "store5_menu4", "Simple look with flavourful green curry soup spiced with organic herbs", 10.50));
        category.add(new CategoryDomain("Braised Pork Soup", "store5_menu5", "Aromatic long-boiled soup served with tender pork trotters and duck eggs.", 11.80));
        category.add(new CategoryDomain("Tum Talay", "store5_menu6", "Spicy seafood papaya salad.", 12.20));
        category.add(new CategoryDomain("Crispy Pork Belly", "store5_menu7", "Golden, crispy and thick pork belly freshly cooked daily.", 13.80));


        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}