package com.example.q_less_java;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class HistoryArrayAdapter extends ArrayAdapter<String> {

    public HistoryArrayAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.history_item, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the custom layout for each history item
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_item, parent, false);
        }

        // Get references to the TextView and ImageButton
        //TextView textView = convertView.findViewById(R.id.history_text);
        ImageButton removeButton = convertView.findViewById(R.id.remove_button);
        Button itemButton = convertView.findViewById(R.id.history_text);

        // Set the text for the TextView to the history item's text
        String item = getItem(position);
        Log.d("Position", item);
        itemButton.setText(item);

        itemButton.setOnClickListener(v ->{
            if (item.equals("McYouTwit Burgers and Fries")) {
                Intent intent = new Intent(getContext(), restaurant1.class);
                getContext().startActivity(intent);
            } else if (item.equals("Malone's Cone")) {
                Intent intent = new Intent(getContext(), restaurant2.class);
                getContext().startActivity(intent);

            } else if (item.equals("Ah Huat Noodle Soup")) {
                Intent intent = new Intent(getContext(), restaurant3.class);
                getContext().startActivity(intent);
            } else if (item.equals("OISHI DESU")) {
                Intent intent = new Intent(getContext(), restaurant4.class);
                getContext().startActivity(intent);
            } else if (item.equals("Thai Thae, Real Thai")) {
                Intent intent = new Intent(getContext(), restaurant5.class);
                getContext().startActivity(intent);
            } else{
                Intent intent = new Intent(getContext(), restaurant_list.class);
                getContext().startActivity(intent);
            }

        });

        // Set the click listener for the remove button
        removeButton.setOnClickListener(v -> {
            this.remove(item);
            this.saveSearchHistory();
        });

        // Return the custom view for this item
        return convertView;
    }


    public void saveSearchHistory() {
        SharedPreferences prefs = getContext().getSharedPreferences("SearchHistoryPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        HashSet<String> set = new HashSet<>(this.getCount());
        for (int i = 0; i < this.getCount(); i++) {
            set.add(this.getItem(i));
        }
        editor.putStringSet("SearchHistory", set);
        editor.apply();
    }
}