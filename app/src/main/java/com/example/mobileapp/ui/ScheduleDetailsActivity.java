package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.data.model.TrainSchedule;

public class ScheduleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);

        TextView startingPointTextView, destinationTextView, availableClassesTextView, stationsTextView, departureTimeTextView, arrivalTimeTextView;
        Button bookButton, backButton;

        // Initialize UI elements
        startingPointTextView = findViewById(R.id.scheduleDetailsStartingPointTextView);
        destinationTextView = findViewById(R.id.scheduleDetailsDestinationTextView);
        availableClassesTextView = findViewById(R.id.scheduleDetailsAvailableClassesTextView);
        stationsTextView = findViewById(R.id.scheduleDetailsStationsTextView);
        departureTimeTextView = findViewById(R.id.scheduleDetailsDepartureTimeTextView);
        arrivalTimeTextView = findViewById(R.id.scheduleDetailsArrivalTimeTextView);
        bookButton = findViewById(R.id.scheduleDetailsBookButton);
        backButton = findViewById(R.id.scheduleDetailsBackButton);

        // Retrieve schedule data passed from AllSchedulesActivity
        TrainSchedule selectedSchedule = getIntent().getParcelableExtra("schedule");

        // Display schedule details
        startingPointTextView.setText("Starting Point: " + selectedSchedule.getDeparture_station());
        destinationTextView.setText("Destination: " + selectedSchedule.getArrival_station());
        availableClassesTextView.setText("Available Classes: " + selectedSchedule.getSeat_classes());
        stationsTextView.setText("Stations: " + selectedSchedule.getIntermediate_stops());
        departureTimeTextView.setText("Departure: " + selectedSchedule.getDeparture_time());
        arrivalTimeTextView.setText("Arrival Time: " + selectedSchedule.getArrival_time());

        // Handle the "Book" button click event
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to BookingActivity
                Intent intent = new Intent(ScheduleDetailsActivity.this, BookingActivity.class);
                // Pass the selected schedule data to BookingActivity
                intent.putExtra("schedule", selectedSchedule);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}