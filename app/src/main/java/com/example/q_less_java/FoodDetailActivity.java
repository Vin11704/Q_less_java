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

        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_detail);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */

        // Retrieve food details from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String foodName = extras.getString("food_name");
            String foodDescLong = extras.getString("food_desc_long");
            int foodImageResId = extras.getInt("food_image");

            // Set food details to views
            TextView foodNameTextView = findViewById(R.id.food_name_detail);
            TextView foodDescTextView = findViewById(R.id.food_desc_long_detail);
            ImageView foodImageView = findViewById(R.id.food_image_detail);

            foodNameTextView.setText(foodName);
            foodDescTextView.setText(foodDescLong);
            foodImageView.setImageResource(foodImageResId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar back button click here
        if (item.getItemId() == android.R.id.home) {
            String comment = ((TextInputEditText) findViewById(R.id.comment_text)).getText().toString();
            Intent intent = new Intent();
            intent.putExtra("comment", comment);
            setResult(RESULT_OK, intent);
            finish(); // Close the activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}