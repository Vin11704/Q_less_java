package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private Switch cutlerySwitch;
    private TextView textSelectedItems;
    private TextView subtotalValue;
    private TextView serviceFeeValue;
    private TextView totalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize TextViews
        subtotalValue = findViewById(R.id.subtotalValue);
        serviceFeeValue = findViewById(R.id.serviceFeeValue);
        totalValue = findViewById(R.id.totalValue);

        // Initialize other views
        cutlerySwitch = findViewById(R.id.cutlery_switch);
        cutlerySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView cutleryText = findViewById(R.id.cutleryText);
                TextView cutleryDescription = findViewById(R.id.cutleryDescription);
                if (isChecked) {
                    cutleryText.setText("Cutlery requested");
                    cutleryDescription.setText("Cutlery will be provided with your order.");
                } else {
                    cutleryText.setText("No cutlery");
                    cutleryDescription.setText("Thank you for helping to reduce waste!");
                }
            }
        });

        textSelectedItems = findViewById(R.id.text_selected_items);
        Button buttonPlaceOrder = findViewById(R.id.button_place_order);

        // Set onClickListener for the place order button
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, OrderConfirmation.class);
                startActivity(intent);
            }
        });
    }

}