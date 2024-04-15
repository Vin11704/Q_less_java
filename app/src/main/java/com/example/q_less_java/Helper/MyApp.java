package com.example.q_less_java.Helper;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Reset data when the app starts
        resetData();
    }

    private void resetData() {
        // Perform the data reset operation here
        // For example, clear preferences, databases, or any other storage mechanisms
        // For TinyDB, you can clear all data using clear() method
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        tinyDB.clear();
    }
}