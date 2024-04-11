package com.example.q_less_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class Search extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ListView historyListView;
    String[] foodlist = {"McYouTwit Burgers and Fries", "Malone's Cone", "Ah Huat Chicken Rice Noodle",
            "Burger Combo", "Fries","Burger Bundle", "Matcha", "Caramel", "Brownie Sundae", "Beef Noodle","Seafood Rice Noodle", "Duck Noodle", "Chicken Noodle", "Meat Lover Bowl"};
    ArrayAdapter<String> arrayAdapter;
    HistoryArrayAdapter historyAdapter;
    ArrayList<String> searchHistoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_bar);
        listView = findViewById(R.id.list_item);
        historyListView = findViewById(R.id.history_list);
        ImageButton imageButton = findViewById(R.id.back_button);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, HomePage.class);
                startActivity(intent);
            }
        });

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodlist);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
                Toast.makeText(Search.this, "You Click -" + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Search.this, restaurant_list.class);
                startActivity(intent);
                addToHistory(adapterView.getItemAtPosition(i).toString());
                listView.setVisibility(View.GONE);
            // Show the search history when there is text in the SearchView
                historyListView.setVisibility(View.VISIBLE);

    });
        loadSearchHistory();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Show the search history when the SearchView is focused
                    historyListView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);

                } else {
                    // Hide the search history when the SearchView loses focus
                    historyListView.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                arrayAdapter.getFilter().filter(query);
                addToHistory(query);
                listView.setVisibility(View.GONE);
                Intent intent = new Intent(Search.this, restaurant_list.class);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    listView.setVisibility(View.VISIBLE);
                    // Show the search history when there is text in the SearchView
                    historyListView.setVisibility(View.GONE);
                } else {
                    // Hide the search history when the text is cleared
                    historyListView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void addToHistory(String query) {
        if (!searchHistoryList.contains(query)) {
            searchHistoryList.add(0, query);
            historyAdapter.notifyDataSetChanged();
            historyListView.setVisibility(View.VISIBLE);
            historyAdapter.saveSearchHistory(); // Call save from adapter
        }
    }

    private void loadSearchHistory() {
        SharedPreferences prefs = getSharedPreferences("SearchHistoryPrefs", MODE_PRIVATE);
        searchHistoryList = new ArrayList<>(prefs.getStringSet("SearchHistory", new HashSet<>()));
        historyAdapter = new HistoryArrayAdapter(this, searchHistoryList);
        historyListView.setAdapter(historyAdapter);
    }


}