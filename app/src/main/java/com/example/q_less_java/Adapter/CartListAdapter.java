package com.example.q_less_java.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.q_less_java.Domain.CategoryDomain;
import com.example.q_less_java.Helper.CartManager;
import com.example.q_less_java.Interface.ChangeNumberItemsListener;
import com.example.q_less_java.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<CategoryDomain> foodDomains;
    private com.example.q_less_java.Helper.CartManager cartManager;
    private ChangeNumberItemsListener changeNumberItemsListener;

     public CartListAdapter(ArrayList<CategoryDomain> foodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDomains = foodDomains;
        this.cartManager = new CartManager(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getPrice()));
//        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getPrice())*100) / 100));
//        holder.totalEachItem.setText(String.valueOf(foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getPrice()));
        double totalEachItem = foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getPrice();
        holder.totalEachItem.setText(String.format("%.2f", totalEachItem));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartManager.plusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });

            }
        });

        holder.removeFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartManager.minusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic, addToCart, removeFromCart;
        TextView totalEachItem, num;

        // link to the viewholder XML file
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem = itemView.findViewById(R.id.totalFeeEachItem);
            num = itemView.findViewById(R.id.numberInCart);
            addToCart = itemView.findViewById(R.id.add_ToCartBtn);
            removeFromCart = itemView.findViewById(R.id.remove_fromCartBtn);

        }

    }
}
