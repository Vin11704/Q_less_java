package com.example.q_less_java.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.q_less_java.Domain.CategoryDomain;
import com.example.q_less_java.R;
import com.example.q_less_java.ShowFoodDetailsActivity;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {
    ArrayList<CategoryDomain> categoryDomains;

    // constructor of the adaptor subclass of RecyclerView class which takes in the array of CategoryDomain objects
    public CategoryAdaptor(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }


    // This is the part where we program the cardview in the recyclerview to change based on the elements in the domain
    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.menuName.setText(categoryDomains.get(position).getTitle());
        holder.menuDescription.setText(categoryDomains.get(position).getDescription());
        holder.menuPrice.setText(String.valueOf(categoryDomains.get(position).getPrice()));

        @SuppressLint("DiscouragedApi")
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(categoryDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.menuPic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowFoodDetailsActivity.class);
                intent.putExtra("object", categoryDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    // find the total number of items inside the domain
    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    // Template reference of the attributes/files in the items/objects to be displayed through ViewHolder_category
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuName;
        TextView menuDescription;
        TextView menuPrice;
        ImageView menuPic;
        ImageView addBtn;
        CardView menuCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.menu_title);
            menuDescription = itemView.findViewById(R.id.menu_description);
            menuPrice = itemView.findViewById(R.id.menu_price);
            menuPic = itemView.findViewById(R.id.menu_profile);
            addBtn = itemView.findViewById(R.id.addBtn);
            menuCart = itemView.findViewById(R.id.menu_cart);
        }
    }
}
