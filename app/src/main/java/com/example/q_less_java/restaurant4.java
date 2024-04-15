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

public class restaurant4 extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;


    ImageView store4_arrow;

    CardView viewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant4);

        recyclerViewCategoryList();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Import clickable items
        store4_arrow = findViewById(R.id.store4_arrow);
        viewCard = findViewById(R.id.viewCart);

        // Making the items clickable and link to sub-pages
        store4_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant4.this, restaurant_list.class);
                startActivity(intent);
            }
        });


        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant4.this, CartListActivity.class);
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
        category.add(new CategoryDomain("Teriyaki Chicken", "store4_menu1", "Sweet and intense sauce on marinated chicken, giving tender skin and juicy meat", 9.30));
        category.add(new CategoryDomain("Pork Katsu Curry", "store4_menu2","Golden colored crispy pork, cooked with fresh sunflower oil.", 8.50));
        category.add(new CategoryDomain("Omurice Special", "store4_menu3", "Flavourful authentic Tokyo fried rice covered in fresh and juicy omelette.", 10.00));
        category.add(new CategoryDomain("Pork Ramen", "store4_japanese", "Chewy ramen noodle served with long-boiled pig-bones broth, topped with onsen egg.", 12.80));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}