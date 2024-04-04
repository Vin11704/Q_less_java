package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class FoodDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve planet details from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String foodName = extras.getString("food_name");
            String foodDesc = extras.getString("food_desc");
            int foodImageResId = extras.getInt("food_image");

            // Set planet details to views
            TextView foodNameTextView = findViewById(R.id.food_name_detail);
            TextView foodDescTextView = findViewById(R.id.food_desc_detail);
            ImageView foodImageView = findViewById(R.id.food_image_detail);

            foodNameTextView.setText(foodName);
            foodDescTextView.setText(foodDesc);
            foodImageView.setImageResource(foodImageResId);
        }
    }

    @Override
    public void onBackPressed() {
        String comment = ((TextInputEditText) findViewById(R.id.comment_text)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra("comment", comment);
        setResult(RESULT_OK, intent);
        super.onBackPressed(); // Close the activity
        finish(); // Close the activity
    }

}