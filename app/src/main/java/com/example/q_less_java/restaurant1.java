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

public class restaurant1 extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;

    ImageView store1_arrow;

    CardView viewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant1);

        recyclerViewCategoryList();

        // Import clickable items
        store1_arrow = findViewById(R.id.store1_arrow);
        viewCard = findViewById(R.id.viewCart1);

        // Making the items clickable and link to sub-pages
        store1_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant1.this, restaurant_list.class);
                startActivity(intent);
            }
        });

        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant1.this, CartListActivity.class);
                startActivity(intent);
            }
        });

    }

    // Initiate recyclerview feature
    private void recyclerViewCategoryList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);


        // This will be the list of all the items that we want to display in the RecyclerView, in which CategoryDomain is the function to create new displaying object with specific attributes/files
        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("McYouTwit Burgers and Fries", "store1_burger", "Our signature burger featuring a juicy beef patty cooked to perfection, topped with melt…", 12.80));
        category.add(new CategoryDomain("Signature McYouTwit Burger Combo", "store1_menu2","Our mouthwatering signature burger paired  with a refreshing drink of your choice.", 13.50));
        category.add(new CategoryDomain("Double Signature Burger Bundle", "store1_menu3", "Two mouthwatering signature burgers with your choice of sides. Perfect for sharing.", 24.90));
        category.add(new CategoryDomain("McYouTwit Fries in a Bucket", "store1_menu4", "Golden and crunchy on the outside, fluffy on the inside, and seasoned to perfection...", 3.90));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}