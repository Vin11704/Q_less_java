package com.example.q_less_java;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private Context context;
    private String[] restaurantNames;
    private int[] profiles;
    private OnItemClickListener listener;

    public RestaurantAdapter(Context context, String[] restaurantNames, int[] profiles) {
        this.context = context;
        this.restaurantNames = restaurantNames;
        this.profiles = profiles;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String restaurantName = restaurantNames[position];
        holder.bind(restaurantName, position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
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
        private CardView cardView;
        private TextView textView;
        private ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.store);
            textView = itemView.findViewById(R.id.textView_restaurant_name);
            imgView = itemView.findViewById(R.id.store_profile);
        }

        public void bind(String restaurantName, int pos) {
            textView.setText(restaurantName);
            imgView.setImageResource(profiles[pos]);
        }
    }
}
