
package com.example.q_less_java;

import android.annotation.SuppressLint;
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


        tax = Math.round((cartManager.getTotalFee() * percentTax) * 100 / 100);
        double total = Math.round((cartManager.getTotalFee() + tax ) * 100) / 100;
        double itemTotal = Math.round(cartManager.getTotalFee() * 100) / 100;

        subtotal.setText("$" + itemTotal);
        taxFee.setText("$" + tax);
        orderTotal.setText("$" + total);
    }
}























