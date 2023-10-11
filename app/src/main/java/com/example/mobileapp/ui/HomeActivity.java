package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapp.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button viewAllSchedulesButton = findViewById(R.id.viewAllSchedulesButton);
        Button viewAllBookingsButton = findViewById(R.id.viewAllBookingsButton);

        // Set an OnClickListener for the button
        viewAllSchedulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to AllSchedulesActivity
                Intent intent = new Intent(HomeActivity.this, AllSchedulesActivity.class);
                startActivity(intent);
            }
        });

        viewAllBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to AllSchedulesActivity
                Intent intent = new Intent(HomeActivity.this, AllBookingsActivity.class);
                startActivity(intent);
            }
        });

    }
}