package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.data.model.Reservation;
import com.example.mobileapp.data.model.TrainSchedule;
import com.example.mobileapp.login.SessionManagement;
import com.example.mobileapp.utils.Utils;

import org.bson.types.ObjectId;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Activity class for handling the train booking process, including selecting travel details and making reservations.
 */
public class BookingActivity extends AppCompatActivity {

    private String selectedFrom = "";
    private String selectedTo = "";
    private String selectedClass = "";

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

        SessionManagement sessionManagement = new SessionManagement(BookingActivity.this);
        String nic = sessionManagement.getSessionNIC();

        TextView scheduleDetailsTextView, trainFromTextView, trainToTextView, nicTextView, expandCollapseButton;
        EditText noOfSeatsEditText, seatNumberEditText;
        Button continueButton, backButton;
        Spinner fromSpinner, toSpinner, classesSpinner;

        // Initialize UI elements
        scheduleDetailsTextView = findViewById(R.id.bookingTrainNameTextView);
        trainFromTextView = findViewById(R.id.bookingDepTextView);
        trainToTextView = findViewById(R.id.bookingArrTextView);
        nicTextView = findViewById(R.id.bookingNICEditText);
        seatNumberEditText = findViewById(R.id.bookingSeatNumberEditText);
        fromSpinner = findViewById(R.id.bookingFromSpinner);
        toSpinner = findViewById(R.id.bookingToSpinner);
        classesSpinner = findViewById(R.id.bookingClassSpinner);
        noOfSeatsEditText = findViewById(R.id.bookingSeatNumberEditText);
        continueButton = findViewById(R.id.bookingContinueButton);
        backButton = findViewById(R.id.bookingBackButton);
        expandCollapseButton = findViewById(R.id.bookingExpandCollapseButton);

        backButton.setText("< Back");

        // Retrieve schedule data passed from ScheduleActivity
        TrainSchedule selectedSchedule = getIntent().getParcelableExtra("schedule");

        // Create ArrayAdapter for each Spinner
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectedSchedule.getIntermediate_stops());
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectedSchedule.getIntermediate_stops());
        ArrayAdapter<String> classesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectedSchedule.getSeat_classes());

        // Set the dropdown layout style for each Spinner
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Display schedule details
        nicTextView.setText(nic);
        scheduleDetailsTextView.setText(selectedSchedule.getTrain_name());
        trainFromTextView.setText(selectedSchedule.getDeparture_station() + ": " + selectedSchedule.getDeparture_time());
        trainToTextView.setText(selectedSchedule.getArrival_station() + ": " +selectedSchedule.getArrival_time());
        noOfSeatsEditText.setText("0");

        fromSpinner.setAdapter(fromAdapter);
        toSpinner.setAdapter(toAdapter);
        classesSpinner.setAdapter(classesAdapter);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedFrom = selectedSchedule.getIntermediate_stops().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedFrom = selectedSchedule.getDeparture_station();
            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedTo = selectedSchedule.getIntermediate_stops().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedTo = selectedSchedule.getArrival_station();
            }
        });

        classesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedClass = selectedSchedule.getSeat_classes().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedClass = selectedSchedule.getSeat_classes().get(0);
            }
        });

        Reservation newReservation = new Reservation();

        // Handle the "Continue" button click event (simulate for now)
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newReservation.setReservation_number(Utils.getRandomNumber());
                newReservation.setReference_id(nic);
                newReservation.setTrain_id(selectedSchedule.getTrain_number());
                newReservation.setTrain_name(selectedSchedule.getTrain_name());
                newReservation.setTravel_route(selectedSchedule.getDeparture_station() + " - " + selectedSchedule.getArrival_station());
                newReservation.setFrom(selectedFrom);
                newReservation.setTo(selectedTo);

                int selectedClassInInt = Utils.getTrainClassAsNumber(selectedClass);
                int noOfTickets = Integer.parseInt(noOfSeatsEditText.getText().toString());
                newReservation.setTicket_class(selectedClassInInt);
                newReservation.setNumber_of_tickets(noOfTickets);
                newReservation.setTotal_price(Utils.getTotal(selectedClassInInt, noOfTickets));
                newReservation.setBooking_date(formattedDate);
                if (expandCollapseButton.getText().toString().equals("Select a date")) {
                    newReservation.setReservation_date(formattedDate);
                } else {
                    newReservation.setReservation_date(expandCollapseButton.getText().toString());
                }
                newReservation.setStatus("Active");
                // Generate an Object ID
                ObjectId objectId = new ObjectId();

                newReservation.setId(objectId.toString());

                String invalidData = Utils.validateData(newReservation, selectedSchedule);
                if(invalidData.equals("")) {
                    // Create an Intent to navigate to BookingActivity
                    Intent intent = new Intent(BookingActivity.this, BookingConfirmationActivity.class);
                    // Pass the selected schedule data to BookingActivity
                    intent.putExtra("schedule", selectedSchedule);
                    intent.putExtra("reservation", newReservation);

                    startActivity(intent);
                } else {
                    Toast.makeText(BookingActivity.this, invalidData, Toast.LENGTH_SHORT).show();
                }
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

    // Function to handle the date-picker validation and visibility
    public void toggleDatePickerVisibility(View view) {
        DatePicker datePicker = findViewById(R.id.bookingDatePicker);
        TextView expandCollapseButton = findViewById(R.id.bookingExpandCollapseButton);

        // Get the current date
        Calendar currentDate = Calendar.getInstance();

        // Set the minimum date for the DatePicker to the current date
        datePicker.setMinDate(currentDate.getTimeInMillis());

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