
package com.example.q_less_java;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.q_less_java.Adapter.CartListAdapter;
import com.example.q_less_java.Helper.CartManager;
import com.example.q_less_java.Interface.ChangeNumberItemsListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;

    private CartManager cartManager;

    TextView restaurantName, pickup, distance, address, subtotal, taxFee, orderTotal, placeOrder;

    ImageView cartBackBtn;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_list);

        cartManager = new CartManager(this);

        initView();
        initList();
        CalculateCart();
        getCurrentDateTime();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Generate the order ID
                String orderId = generateOrderID();

                // Get the current data and time
                String currentDateTime = getCurrentDateTime();


                Intent intent = new Intent(CartListActivity.this, OrderConfirmation.class);
                intent.putExtra("ORDER_TIME", currentDateTime);
                intent.putExtra("ORDER_ID", orderId);
                startActivity(intent);
            }
        });


    }


    private String generateOrderID(){
        Random random = new Random();
        StringBuilder orderId = new StringBuilder("#");

        // Append 6 digits
        for (int i = 0; i < 6; i++){
            int digit = random.nextInt(10);         // Generate a single digit (0-9)
            orderId.append(digit);
        }

        char letter = (char) ('A' + random.nextInt(26));    // Generate a letter between A - Z
        orderId.append(letter);

        return orderId.toString();

    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, h:mma", Locale.getDefault());
        return sdf.format(new Date()).toUpperCase();
    }

    private void initView() {
        recyclerViewList  = findViewById(R.id.cardList);
        restaurantName = findViewById(R.id.restaurantName);
        pickup = findViewById(R.id.pickUp);
        distance = findViewById(R.id.distance);
        address = findViewById(R.id.address);
        subtotal = findViewById(R.id.subtotal);
        taxFee = findViewById(R.id.taxFee);
        orderTotal = findViewById(R.id.orderTotal);
        placeOrder = findViewById(R.id.placeOrder);
        scrollView = findViewById(R.id.scrollView3);
        cartBackBtn = findViewById(R.id.cart_backBtn);

    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter = new CartListAdapter(cartManager.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);

    }


    @SuppressLint("SetTextI18n")
    private void CalculateCart(){
        double percentTax = 0.09;


//        tax = Math.round((cartManager.getTotalFee() * percentTax) * 100 / 100);
//        double total = Math.round((cartManager.getTotalFee() + tax ) * 100) / 100;
//        double itemTotal = Math.round(cartManager.getTotalFee() * 100) / 100;
//
//        subtotal.setText("$" + itemTotal);
//        taxFee.setText("$" + tax);
//        orderTotal.setText("$" + total);

        double tax = cartManager.getTotalFee() * percentTax;
        double total = cartManager.getTotalFee() + tax;
        double itemTotal = cartManager.getTotalFee();

        subtotal.setText(String.format("$%.2f", itemTotal));
        taxFee.setText(String.format("$%.2f", tax));
        orderTotal.setText(String.format("$%.2f", total));

    }
}























