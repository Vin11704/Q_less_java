package com.example.q_less_java.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.q_less_java.Domain.RestaurantDomain;
import com.example.q_less_java.R;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    public static RestaurantAdapter.OnItemClickListener OnItemClickListener;
    ArrayList<RestaurantDomain> restaurantDomains;
    private OnItemClickListener listener;

//    private Context context;
//    private String[] restaurantNames;
//    private int[] profiles;

//    public RestaurantAdapter(Context context, String[] restaurantNames, int[] profiles) {
//        this.context = context;
//        this.restaurantNames = restaurantNames;
//        this.profiles = profiles;
//    }

    public RestaurantAdapter(ArrayList<RestaurantDomain> restaurantDomains, OnItemClickListener listener) {
        this.restaurantDomains = restaurantDomains;
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        RestaurantDomain restaurant = restaurantDomains.get(position);

        holder.rest_name.setText(restaurantDomains.get(position).getTitle());
        holder.rest_des.setText(restaurantDomains.get(position).getDescription());
        holder.rest_pick_up.setText(restaurantDomains.get(position).getPickup_in());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(restaurantDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.rest_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View cardView;
        TextView rest_name;
        TextView rest_des;
        TextView rest_pick_up;
        ImageView rest_pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rest_name = itemView.findViewById(R.id.rest_name);
            rest_des = itemView.findViewById(R.id.rest_des);
            rest_pick_up = itemView.findViewById(R.id.rest_pick_up);
            rest_pic = itemView.findViewById(R.id.rest_pic);
        }
    }
}
