package com.example.q_less_java;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView textView = convertView.findViewById(R.id.history_text);
        ImageButton removeButton = convertView.findViewById(R.id.remove_button);

        // Set the text for the TextView to the history item's text
        String item = getItem(position);
        Log.d("Position", item);
        textView.setText(item);

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