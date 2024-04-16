package com.example.q_less_java;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.q_less_java.Domain.CategoryDomain;
import com.example.q_less_java.Helper.CartManager;


public class ShowFoodDetailsActivity extends AppCompatActivity {


    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, addToCartBtn;
    private ImageView addBtn, removeBtn,picFood, backBtn;

    private CategoryDomain object;

    int numberOrder = 0;

    private CartManager cartManager;



    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_food_details);

        cartManager = new CartManager(this);

        initView();

        getBundle();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void getBundle() {
        object = (CategoryDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);


        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));

            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder > 0){
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));

            }
        });

        // add objects/items into cart --> the objects are based on the CategoryDomain type
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                cartManager.insertFood(object);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.menu_title);
        feeTxt = findViewById(R.id.menu_price);
        descriptionTxt = findViewById(R.id.menu_description);
        numberOrderTxt = findViewById(R.id.menu_quantity);
        addBtn = findViewById(R.id.add_qnt);
        removeBtn = findViewById(R.id.remove_qnt);
        picFood = findViewById(R.id.menu_pic);
        backBtn = findViewById(R.id.back_button);
    }
}