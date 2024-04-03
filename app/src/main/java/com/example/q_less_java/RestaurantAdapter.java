package com.example.q_less_java;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private Context context;
    private String[] restaurantNames;
    private OnItemClickListener listener;

    public RestaurantAdapter(Context context, String[] restaurantNames) {
        this.context = context;
        this.restaurantNames = restaurantNames;
    }

    public InputFilter getFilter() {
        return null;
    }

    public interface OnItemClickListener {
        void onItemClick(String selectedRestaurant);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String restaurantName = restaurantNames[position];
        holder.bind(restaurantName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(restaurantName);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String restaurantName) {
            textView.setText(restaurantName);
        }
    }
}
