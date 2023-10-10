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
import com.example.mobileapp.data.model.TrainSchedule;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Get the current date
        Date currentDate = new Date();
        // Define the date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        // Format the current date as a string in the desired format
        String formattedDate = sdf.format(currentDate);

        TextView scheduleDetailsTextView, trainFromTextView, trainToTextView, nicTextView, expandCollapseButton;
        EditText seatNumberEditText, fromEditText, toEditText, classesEditText, noOfSeatsEditText;
        Button continueButton, backButton;

        // Initialize UI elements
        scheduleDetailsTextView = findViewById(R.id.bookingTrainNameTextView);
        trainFromTextView = findViewById(R.id.bookingDepTextView);
        trainToTextView = findViewById(R.id.bookingArrTextView);
        nicTextView = findViewById(R.id.bookingNICEditText);
        seatNumberEditText = findViewById(R.id.bookingSeatNumberEditText);
        fromEditText = findViewById(R.id.bookingFromEditText);
        toEditText = findViewById(R.id.bookingToEditText);
        classesEditText = findViewById(R.id.bookingClassEditText);
        noOfSeatsEditText = findViewById(R.id.bookingSeatNumberEditText);
        continueButton = findViewById(R.id.bookingContinueButton);
        backButton = findViewById(R.id.bookingBackButton);
        expandCollapseButton = findViewById(R.id.bookingExpandCollapseButton);

        // Retrieve schedule data passed from ScheduleActivity
        TrainSchedule selectedSchedule = getIntent().getParcelableExtra("schedule");

        // Display schedule details
        scheduleDetailsTextView.setText(selectedSchedule.getTrain_name());
        trainFromTextView.setText(selectedSchedule.getDeparture_station() + ": " + selectedSchedule.getDeparture_time());
        trainToTextView.setText(selectedSchedule.getArrival_station() + ": " +selectedSchedule.getArrival_time());
        fromEditText.setText(selectedSchedule.getDeparture_station());
        toEditText.setText(selectedSchedule.getArrival_station());
        classesEditText.setText(selectedSchedule.getSeat_classes().get(0));
        noOfSeatsEditText.setText("0");

        Reservation newReservation = new Reservation();

        // Handle the "Continue" button click event (simulate for now)
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newReservation.setReservation_number(getRandomNumber());
                newReservation.setReference_id("2000253020802"); // TODO: replace with the actual data
                newReservation.setTrain_id(selectedSchedule.getTrain_number());
                newReservation.setTrain_name(selectedSchedule.getTrain_name());
                newReservation.setTravel_route(selectedSchedule.getDeparture_station() + " - " + selectedSchedule.getArrival_station());
                newReservation.setFrom(fromEditText.getText().toString());
                newReservation.setTo(toEditText.getText().toString());

                int selectedClass = getTrainClass(classesEditText.getText().toString());
                int noOfTickets = Integer.parseInt(noOfSeatsEditText.getText().toString());
                newReservation.setTicket_class(selectedClass);
                newReservation.setNumber_of_tickets(noOfTickets);
                newReservation.setTotal_price(getTotal(selectedClass, noOfTickets));
                newReservation.setBooking_date(formattedDate);
                if (expandCollapseButton.getText().toString().equals("Select a date")) {
                    newReservation.setReservation_date(formattedDate);
                } else {
                    newReservation.setReservation_date(expandCollapseButton.getText().toString());
                }
                newReservation.setStatus("Created");
                // Generate an Object ID
                ObjectId objectId = new ObjectId();

                newReservation.setId(objectId.toString());
                Log.i("Reservation", "Id: " + newReservation.getId());
                Log.i("Reservation", "Reservation No: " + newReservation.getReservation_number());
                Log.i("Reservation", "Reference ID: " + newReservation.getReference_id());
                Log.i("Reservation", "Train ID: " + newReservation.getTrain_id());
                Log.i("Reservation", "Train Name: " + newReservation.getTrain_name());
                Log.i("Reservation", "Travel Route: " + newReservation.getTravel_route());
                Log.i("Reservation", "From: " + newReservation.getFrom());
                Log.i("Reservation", "To: " + newReservation.getTo());
                Log.i("Reservation", "Ticket Class: " + newReservation.getTicket_class());
                Log.i("Reservation", "Number of Tickets: " + newReservation.getNumber_of_tickets());
                Log.i("Reservation", "Total Price: " + newReservation.getTotal_price());
                Log.i("Reservation", "Booking Date: " + newReservation.getBooking_date());
                Log.i("Reservation", "Reservation Date: " + newReservation.getReservation_date());

                // Create an Intent to navigate to BookingActivity
                Intent intent = new Intent(BookingActivity.this, BookingConfirmationActivity.class);
                // Pass the selected schedule data to BookingActivity
                intent.putExtra("schedule", selectedSchedule);
                intent.putExtra("reservation", newReservation);

                startActivity(intent);
            }
        });

        backButton.setOnClickListener(v -> finish());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int getTotal(int selectedClass, int noOfTickets) {
        return selectedClass * noOfTickets * 20;
    }

    private int getTrainClass(String toString) {
        if (toString.equals("First-Class")) {
            return 1;
        } else if (toString.equals("Second-Class")) {
            return 2;
        } else {
            return 3;
        }
    }

    public void toggleDatePickerVisibility(View view) {
        DatePicker datePicker = findViewById(R.id.bookingDatePicker);
        TextView expandCollapseButton = findViewById(R.id.bookingExpandCollapseButton);

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

    public String getRandomNumber() {
        // Generate a random double between 0 (inclusive) and 1 (exclusive)
        double randomDouble = Math.random();

        // Generate a random number between 1 and 10000
        int min = 1;
        int max = 10000;
        int randomNumber = (int) (Math.random() * (max - min + 1)) + min;

        return Integer.toString(randomNumber);
    }
}