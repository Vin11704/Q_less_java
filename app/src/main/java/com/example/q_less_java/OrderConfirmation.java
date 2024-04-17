package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmation extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView date_time = findViewById(R.id.date_time);
        TextView orderId_num = findViewById(R.id.order_id_number);
        Button button_order_summary = findViewById(R.id.order_summary);


        // button functions

        button_order_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmation.this, HomePage.class);
                startActivity(intent);
            }
        });

        // To show order time
        String orderTime = getIntent().getStringExtra("ORDER_TIME");
        if (orderTime != null){
            date_time.setText(orderTime);
        }

        // To show order id number
        String orderId = getIntent().getStringExtra("ORDER_ID");
        if (orderId != null){
            orderId_num.setText(orderId);
        }

    }

}