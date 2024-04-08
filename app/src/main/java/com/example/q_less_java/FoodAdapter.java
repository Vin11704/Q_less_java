package com.example.q_less_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {

    private ArrayList<Food> foodArrayList;
    Context context;

    public FoodAdapter(ArrayList<Food> foodArrayList, Context context) {
        super(context, R.layout.item_list_layout, foodArrayList);
        this.foodArrayList = foodArrayList;
        this.context = context;
    }

    private static class MyViewHolder{
        TextView foodName;
        TextView foodDesc;
        ImageView foodImage;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Food foods = getItem(position);

        MyViewHolder myViewHolder = new MyViewHolder();
        final View result;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(
                    R.layout.item_list_layout,
                    parent,
                    false
            );

            myViewHolder.foodName = (TextView) convertView.findViewById(R.id.food_name);
            myViewHolder.foodDesc = (TextView) convertView.findViewById(R.id.food_desc);
            myViewHolder.foodImage = (ImageView) convertView.findViewById(R.id.food_image);

            result = convertView;
            convertView.setTag(myViewHolder);


        }else{
            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;
        }

        myViewHolder.foodName.setText(foods.getFoodName());
        myViewHolder.foodDesc.setText(foods.getFoodDesc());
        myViewHolder.foodImage.setImageResource(foods.getFoodImage());

        return result;
    }
}
