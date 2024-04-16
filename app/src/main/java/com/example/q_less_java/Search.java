package com.example.q_less_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Search extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ListView historyListView;
    String[] foodlist = {"McYouTwit Burgers and Fries", "Malone's Cone", "Ah Huat Noodle Soup",
            "Burger Combo", "Fries","Burger Bundle", "Matcha", "Caramel", "Brownie Sundae", "Beef Noodle","Seafood Rice Noodle", "Duck Noodle", "Chicken Noodle", "Meat Lover Bowl","OISHI DESU","Thai Thae, Real Thai"};
    ArrayAdapter<String> arrayAdapter;
    HistoryArrayAdapter historyAdapter;
    ArrayList<String> searchHistoryList;
    private Map<String, Class<?>> searchMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_bar);
        listView = findViewById(R.id.list_item);
        historyListView = findViewById(R.id.history_list);
        ImageButton imageButton = findViewById(R.id.back_button);

        searchMap = new HashMap<>();
        searchMap.put("McYouTwit Burgers and Fries", restaurant1.class);
        searchMap.put("Malone's Cone", restaurant2.class);
        searchMap.put("Ah Huat Noodle Soup", restaurant3.class);
        searchMap.put("OISHI DESU", restaurant4.class);
        searchMap.put("Thai Thae, Real Thai", restaurant5.class);

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
            String selectedItem = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(Search.this, "You Click -" + selectedItem, Toast.LENGTH_SHORT).show();
            // Use the map to find the corresponding activity class
            Class<?> activityClass = searchMap.getOrDefault(selectedItem, restaurant_list.class);

            // Create an intent to start the appropriate activity
            Intent intent = new Intent(Search.this, activityClass);
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
                Class<?> activity = searchMap.getOrDefault(query, restaurant_list.class);

                // Start the activity associated with the query
                Intent intent = new Intent(Search.this, activity);
                startActivity(intent);

                return true;
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