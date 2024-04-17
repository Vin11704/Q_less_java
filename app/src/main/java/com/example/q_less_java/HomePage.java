package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.q_less_java.Adapter.RestaurantAdapter;
import com.example.q_less_java.Domain.RestaurantDomain;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements RestaurantAdapter.OnItemClickListener{
    private RestaurantAdapter adapter;
    private RecyclerView recyclerViewRestaurantList;


    private final String[] restaurantNames = {"McYouTwit Burgers and Fries", "Malone's Cone", "Ah Huat Chicken Rice Noodle"};
//    private final int[] profiles = {R.drawable.store1_burger, R.drawable.store2_ice_cream,
//            R.drawable.store3_rice_noodle};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Add fragment dynamically
        CameraPosition cam = new CameraPosition.Builder().target(new LatLng(1.3406,103.9632)).zoom(18).build();
        SupportMapFragment mapFragment = SupportMapFragment.newInstance(new GoogleMapOptions().camera(cam));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map, mapFragment)
                .commit();

        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_HYBRID)
                .compassEnabled(false)
                .rotateGesturesEnabled(false)
                .tiltGesturesEnabled(false);
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.q_less__color));

        // Populate list view with restaurant names
        final SearchView searchView = findViewById(R.id.searchView);

//        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
//        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new RestaurantAdapter(this, restaurantNames, profiles);
//        restaurantRecyclerView.setAdapter(adapter); //creating views for each item in the data set.

        // Set item click listener to be notified when an item in the RecyclerView is clicked
        //RestaurantAdapter class defines this interface


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(HomePage.this, Search.class);
                startActivity(searchIntent);
            }
        });

//        adapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(String selectedRestaurant) {
//                // You can navigate to the restaurant details activity or perform any other action here
//                Toast.makeText(HomePage.this, "Selected restaurant: " + selectedRestaurant, Toast.LENGTH_SHORT).show();
//
//                // Determine which restaurant was clicked based on its name
//                if (selectedRestaurant.equals(restaurantNames[0])) {
//                    // Launch activity for restaurant1
//                    Intent intent = new Intent(HomePage.this, restaurant1.class);
//                    startActivity(intent);
//                } else if (selectedRestaurant.equals(restaurantNames[1])) {
//                    // Launch activity for restaurant2
//                    Intent intent = new Intent(HomePage.this, restaurant2.class);
//                    startActivity(intent);
//                } else if (selectedRestaurant.equals(restaurantNames[2])) {
//                    // Launch activity for restaurant3
//                    Intent intent = new Intent(HomePage.this, restaurant3.class);
//                    startActivity(intent);
//                }
//            }
//        });

        // Setup search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        recyclerViewRestaurantList();
    }

    // Recyclerview for restaurants
    private void recyclerViewRestaurantList(){

        ArrayList<RestaurantDomain> restaurant = new ArrayList<>();
        restaurant.add(new RestaurantDomain("McYouTwit Burgers and Fries", "store1_burger", "4.3 (102) | Western | 0.2km away", "11"));
        restaurant.add(new RestaurantDomain("Malones Cones", "store2_ice_cream", "3.4 (53) | Dessert | 0.3km away", "11"));
        restaurant.add(new RestaurantDomain("Ah Huat Noodle Soup", "store3_rice_noodle", "4.5 (2k+) | Chinese | 0.2km away", "9"));
        restaurant.add(new RestaurantDomain("Thai Thae, Real Thai", "store5_thai_food", "4.3 (1.3k+) | Thai-Issan | 0.4km away", "15"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewRestaurantList = findViewById(R.id.restaurantRecyclerView);
        adapter = new RestaurantAdapter(restaurant, this);
        recyclerViewRestaurantList.setAdapter(adapter);
        recyclerViewRestaurantList.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void onItemClick(int position) {
        // Handle item click here, based on the clicked position
        // For example, you can start a new activity based on the clicked position
        switch (position) {
            case 0:
                startActivity(new Intent(HomePage.this, restaurant1.class));
                break;
            case 1:
                startActivity(new Intent(HomePage.this, restaurant2.class));
                break;
            case 2:
                startActivity(new Intent(HomePage.this, restaurant3.class));
                break;
            case 3:
                startActivity(new Intent(HomePage.this, restaurant5.class));
                break;
            // Add cases for other positions as needed
        }
    }



    // Inflate menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Handle settings action
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_about) {
            // Handle about action
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}