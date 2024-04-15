package com.example.q_less_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class restaurant_list extends AppCompatActivity {


    CardView store1_cart;

    CardView store2_cart;

    CardView store3_cart;

    CardView store4_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        // Import clickable items
        store1_cart = findViewById(R.id.store1_cart);
        store2_cart = findViewById(R.id.store2_cart);
        store3_cart = findViewById(R.id.store3_cart);
        store4_cart = findViewById(R.id.store4_cart);

        // Making the items clickable and link to sub-pages
        store1_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_list.this, restaurant1.class);
                startActivity(intent);
            }
        });

        store2_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_list.this, restaurant2.class);
                startActivity(intent);
            }
        });

        store3_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_list.this, restaurant3.class);
                startActivity(intent);
            }
        });

        store4_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_list.this, restaurant4.class);
                startActivity(intent);
            }
        });

    }
}