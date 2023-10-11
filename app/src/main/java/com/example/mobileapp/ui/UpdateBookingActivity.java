package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.data.model.Reservation;

import java.util.Date;
import java.util.Locale;

public class UpdateBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking);

        // Get the current date
        Date currentDate = new Date();
        // Define the date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        // Format the current date as a string in the desired format
        String formattedDate = sdf.format(currentDate);

        TextView scheduleDetailsTextView, trainRouteTextView, nicTextView, expandCollapseButton;
        EditText fromEditText, toEditText, classesEditText, noOfSeatsEditText;
        Button updateBookingButton, removeBookingButton;

        Button backButton = findViewById(R.id.updateBookingBackButton);
        backButton.setText("< Back");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize UI elements
        scheduleDetailsTextView = findViewById(R.id.updateBookingTrainNameTextView);
        trainRouteTextView = findViewById(R.id.updateBookingRouteTextView);
        nicTextView = findViewById(R.id.updateBookingNICEditText);
        fromEditText = findViewById(R.id.updateBookingFromEditText);
        toEditText = findViewById(R.id.updateBookingToEditText);
        classesEditText = findViewById(R.id.updateBookingClassEditText);
        noOfSeatsEditText = findViewById(R.id.updateBookingSeatNumberEditText);
        updateBookingButton = findViewById(R.id.updateBookingUpdateButton);
        expandCollapseButton = findViewById(R.id.updateBookingExpandCollapseButton);
        removeBookingButton = findViewById(R.id.removeBookingButton);

        // Retrieve schedule data passed from ScheduleActivity
        Reservation selectedBooking = getIntent().getParcelableExtra("reservation");

        // Display schedule details
        scheduleDetailsTextView.setText(selectedBooking.getTrain_name());
        trainRouteTextView.setText(selectedBooking.getTravel_route());
        fromEditText.setText(selectedBooking.getFrom());
        toEditText.setText(selectedBooking.getTo());
        classesEditText.setText(getTicketClassAsString(selectedBooking.getTicket_class()));
        noOfSeatsEditText.setText(String.valueOf(selectedBooking.getNumber_of_tickets()));
        expandCollapseButton.setText(selectedBooking.getReservation_date());
        nicTextView.setText(selectedBooking.getReference_id());

        // Handle the "Continue" button click event
        updateBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get updated values
                selectedBooking.setFrom(fromEditText.getText().toString());
                selectedBooking.setTo(toEditText.getText().toString());
                selectedBooking.setTicket_class(getTrainClassAsNumber(classesEditText.getText().toString()));
                selectedBooking.setNumber_of_tickets(Integer.parseInt(noOfSeatsEditText.getText().toString()));
                selectedBooking.setReservation_date(expandCollapseButton.getText().toString());
                // Create an Intent to navigate to BookingActivity
                Intent intent = new Intent(UpdateBookingActivity.this, BookingUpdateSummaryActivity.class);
                // Pass the selected schedule data to BookingActivity
                intent.putExtra("reservation", selectedBooking);
                intent.putExtra("type", "update");
                startActivity(intent);
            }
        });

        removeBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to Home
                Intent intent = new Intent(UpdateBookingActivity.this, BookingUpdateSummaryActivity.class);
                intent.putExtra("reservation", selectedBooking);
                intent.putExtra("type", "remove");
                startActivity(intent);
            }
        });

    }

    private String getTicketClassAsString(Integer ticketClass) {
        if (ticketClass == 1){
            return "First-Class";
        } else if (ticketClass == 2) {
            return "Second-Class";
        } else {
            return "Third-Class";
        }
    }

    private int getTotal(int selectedClass, int noOfTickets) {
        if (selectedClass == 1) {
            return 1000 * noOfTickets;
        } else if (selectedClass == 2) {
            return 200 * noOfTickets;
        } else {
            return 30 * noOfTickets;
        }
    }

    private int getTrainClassAsNumber(String toString) {
        if (toString.equals("First-Class")) {
            return 1;
        } else if (toString.equals("Second-Class")) {
            return 2;
        } else {
            return 3;
        }
    }

    public void toggleDatePickerVisibility(View view) {
        DatePicker datePicker = findViewById(R.id.updateBookingDatePicker);
        TextView expandCollapseButton = findViewById(R.id.updateBookingExpandCollapseButton);

        int year = datePicker.getYear();
        int month = datePicker.getMonth(); // Months are zero-based (0 = January)
        int dayOfMonth = datePicker.getDayOfMonth();

        // Toggle the visibility of the DatePicker
        if (datePicker.getVisibility() == View.VISIBLE) {
            datePicker.setVisibility(View.GONE);
            expandCollapseButton.setText("Select Date");
        } else {
            datePicker.setVisibility(View.VISIBLE);
            expandCollapseButton.setText("Done");
        }

        if (year != 0 && month != 0 && dayOfMonth != 0) {
            expandCollapseButton.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
        }
    }
}