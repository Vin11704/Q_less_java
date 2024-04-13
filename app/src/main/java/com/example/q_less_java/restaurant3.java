package com.example.q_less_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class restaurant3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant3);
        ImageButton button_res3 = findViewById(R.id.store3_arrow);

        button_res3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant3.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}