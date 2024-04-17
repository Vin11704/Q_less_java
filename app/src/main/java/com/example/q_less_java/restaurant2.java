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

public class restaurant2 extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;


    ImageView store2_arrow;

    CardView viewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2);

        recyclerViewCategoryList();

        store2_arrow = findViewById(R.id.store2_arrow);
        viewCard = findViewById(R.id.viewCart2);

        // Making the items clickable and link to sub-pages
        store2_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant2.this, restaurant_list.class);
                startActivity(intent);
            }
        });

        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant2.this, CartListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void recyclerViewCategoryList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView2);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);


        // This will be the list of all the items that we want to display in the RecyclerView, in which CategoryDomain is the function to create new displaying object with specific attributes/files
        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Special Super Matcha Scoop", "store2_menu1", "A creamy blend of premium matcha ice cream infused with Japanese ceremonial-grade matcha.", 6.50));
        category.add(new CategoryDomain("Honeyed Caramel Delight", "store2_menu2","Indulge in the decadent fusion of velvety caramel ice cream infused with the pure essence of...", 6.00));
        category.add(new CategoryDomain("Berrylicious Blue Velvet Dream", "store2_menu3", "Immerse yourself in a blissful journey as creamy blueberry-infused ice cream dances on your palate.", 6.00));
        category.add(new CategoryDomain("Sigature Chocolate Brownie Sundae", "store2_menu4", "It\\'s like a party in your mouth – rich, decadent, and oh-so-delicious! Don't miss out on this scrumptious treat. ", 12.50));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}