package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.data.model.TrainSchedule;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        TextView scheduleDetailsTextView;
        EditText nameEditText, emailEditText, seatNumberEditText; // Add more fields as needed
        Button continueButton, backButton;

        // Initialize UI elements
        scheduleDetailsTextView = findViewById(R.id.scheduleDetailsTextView);
        nameEditText = findViewById(R.id.bookingNameEditText);
        emailEditText = findViewById(R.id.bookingEmailEditText);
        seatNumberEditText = findViewById(R.id.bookingSeatNumberEditText);
        continueButton = findViewById(R.id.bookingContinueButton);
        backButton = findViewById(R.id.bookingBackButton);

        // Retrieve schedule data passed from ScheduleActivity
        TrainSchedule selectedSchedule = getIntent().getParcelableExtra("schedule");

        // Display schedule details
        scheduleDetailsTextView.setText("Schedule Details:\n" +
                        "Starting Point: " + selectedSchedule.getDeparture_station() + "\n" +
                        "Destination: " + selectedSchedule.getArrival_station() + "\n"
                // TODO: Display other details similarly
        );

        // Handle the "Continue" button click event (simulate for now)
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulate confirmation and proceed to payment screen
                // You can implement the payment process later
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}