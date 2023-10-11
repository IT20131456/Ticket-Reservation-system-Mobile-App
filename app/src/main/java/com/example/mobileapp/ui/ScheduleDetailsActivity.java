package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        TextView trainNameTextView, descriptionTextView, typeTextView, startingPointTextView, destinationTextView, durationTextView, stopsTextView, availableClassesTextView, seatsTextView;
        Button bookButton, backButton;

        // Initialize UI elements
        trainNameTextView = findViewById(R.id.scheduleDetailsTrainName);
        descriptionTextView = findViewById(R.id.scheduleDetailsDescriptionTextView);
        typeTextView = findViewById(R.id.scheduleDetailsTypeTextView);
        startingPointTextView = findViewById(R.id.scheduleDetailsStartingPointTextView);
        destinationTextView = findViewById(R.id.scheduleDetailsDestinationTextView);
        durationTextView = findViewById(R.id.scheduleDetailsDurationTextView);
        stopsTextView = findViewById(R.id.scheduleDetailsStationsTextView);
        availableClassesTextView = findViewById(R.id.scheduleDetailsAvailableClassesTextView);
        seatsTextView = findViewById(R.id.scheduleDetailsAvailableSeatsTextView);
        bookButton = findViewById(R.id.scheduleDetailsBookButton);
        backButton = findViewById(R.id.scheduleDetailsBackButton);

        // Retrieve schedule data passed from AllSchedulesActivity
        TrainSchedule selectedSchedule = getIntent().getParcelableExtra("schedule");

        // Display schedule details
        trainNameTextView.setText(selectedSchedule.getTrain_name());
        descriptionTextView.setText(selectedSchedule.getTrain_description());
        typeTextView.setText(selectedSchedule.getTrain_type());
        startingPointTextView.setText(selectedSchedule.getDeparture_station() + ": " + selectedSchedule.getDeparture_time());
        destinationTextView.setText(selectedSchedule.getArrival_station() + ": " + selectedSchedule.getArrival_time());
        durationTextView.setText(selectedSchedule.getTravel_duration());
        stopsTextView.setText(selectedSchedule.getIntermediate_stops().toString());
        availableClassesTextView.setText(selectedSchedule.getSeat_classes().toString());
        seatsTextView.setText(selectedSchedule.getNumber_of_seats().toString());

        backButton.setText("< Back");

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