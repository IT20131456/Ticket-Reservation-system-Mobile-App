package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.data.model.TrainSchedule;

import java.util.ArrayList;

/**
 * Activity class for displaying details of a train schedule and providing an option to book a reservation.
 */
public class ScheduleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);

        TextView trainNameTextView, descriptionTextView, typeTextView, startingPointTextView, destinationTextView, durationTextView, stopsTextView, availableClassesTextView;
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
        bookButton = findViewById(R.id.scheduleDetailsBookButton);
        backButton = findViewById(R.id.scheduleDetailsBackButton);

        // Retrieve schedule data passed from AllSchedulesActivity
        TrainSchedule selectedSchedule = getIntent().getParcelableExtra("schedule");

        // Converting the list to comma separated string
        String stops = TextUtils.join(", ", selectedSchedule.getIntermediate_stops());

        ArrayList<String> classesWithSeats = new ArrayList<>();
        // Getting string list for class and separate seat count
        for (int i = 0; i < selectedSchedule.getSeat_classes().size(); i++) {
            String item = selectedSchedule.getSeat_classes().get(i) + ": " + selectedSchedule.getNumber_of_seats().get(i);
            classesWithSeats.add(item);
        }

        String classes = TextUtils.join(", ", classesWithSeats);

        // Display schedule details
        trainNameTextView.setText(selectedSchedule.getTrain_name());
        descriptionTextView.setText(selectedSchedule.getTrain_description());
        typeTextView.setText(selectedSchedule.getTrain_type());
        startingPointTextView.setText(selectedSchedule.getDeparture_station() + ": " + selectedSchedule.getDeparture_time());
        destinationTextView.setText(selectedSchedule.getArrival_station() + ": " + selectedSchedule.getArrival_time());
        durationTextView.setText(selectedSchedule.getTravel_duration());
        stopsTextView.setText(stops);
        availableClassesTextView.setText(classes);

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