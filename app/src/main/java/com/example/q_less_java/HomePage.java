package com.example.q_less_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
public class HomePage extends AppCompatActivity implements OnMapReadyCallback{
    private RecyclerView restaurantRecyclerView;
    private final String[] restaurantNames = {"Restaurant 1", "Restaurant 2", "Restaurant 3",
            "Restaurant 4", "Restaurant 5"};
    private RestaurantAdapter adapter;

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

//        View homepage = getLayoutInflater().inflate(R.layout.activity_homepage, null);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.q_less__color));

        // Populate list view with restaurant names
        final SearchView searchView = findViewById(R.id.searchView);
        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(this, restaurantNames);
        restaurantRecyclerView.setAdapter(adapter); //creating views for each item in the data set.

        // Set item click listener to be notified when an item in the RecyclerView is clicked
        //RestaurantAdapter class defines this interface
        adapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String selectedRestaurant) {
                // You can navigate to the restaurant details activity or perform any other action here
                Toast.makeText(HomePage.this, "Selected restaurant: " + selectedRestaurant, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage .this, RestaurantInfo.class);
                intent.putExtra("info", selectedRestaurant);
                startActivity(intent);
            }
        });

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
    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sg = new LatLng(1.3521, 103.8198);
        googleMap.addMarker(new MarkerOptions()
                .position(sg).title("Marker in Singapore"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sg));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
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
