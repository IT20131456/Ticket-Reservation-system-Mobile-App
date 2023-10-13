package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
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
import com.example.mobileapp.adapter.ScheduleAdapter;
import com.example.mobileapp.api.ApiService;
import com.example.mobileapp.data.model.Reservation;
import com.example.mobileapp.data.model.TrainSchedule;
import com.example.mobileapp.utils.Utils;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity for updating and managing a booking including updating, cancelling, or removing it.
 */
public class UpdateBookingActivity extends AppCompatActivity {
    private TrainSchedule selectedSchedule = new TrainSchedule();
    private ScheduleAdapter scheduleAdapter;
    private String selectedFrom = "";
    private String selectedTo = "";
    private String selectedClass = "";

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

        TextView scheduleDetailsTextView, trainRouteTextView, nicTextView, expandCollapseButton, infoTextView;
        EditText noOfSeatsEditText;
        Button updateBookingButton, removeBookingButton, cancelBookingButton;
        Spinner fromSpinner, toSpinner, classesSpinner;

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
        fromSpinner = findViewById(R.id.updateBookingFromSpinner);
        toSpinner = findViewById(R.id.updateBookingToSpinner);
        classesSpinner = findViewById(R.id.updateBookingClassSpinner);
        noOfSeatsEditText = findViewById(R.id.updateBookingSeatNumberEditText);
        updateBookingButton = findViewById(R.id.updateBookingUpdateButton);
        expandCollapseButton = findViewById(R.id.updateBookingExpandCollapseButton);
        removeBookingButton = findViewById(R.id.removeBookingButton);
        cancelBookingButton = findViewById(R.id.cancelBookingButton);
        infoTextView = findViewById(R.id.infoTextView);

        // Retrieve schedule data passed from ScheduleActivity
        Reservation selectedBooking = getIntent().getParcelableExtra("reservation");

        // Display schedule details
        scheduleDetailsTextView.setText(selectedBooking.getTrain_name());
        trainRouteTextView.setText(selectedBooking.getTravel_route());
        noOfSeatsEditText.setText(String.valueOf(selectedBooking.getNumber_of_tickets()));
        expandCollapseButton.setText(selectedBooking.getReservation_date());
        nicTextView.setText(selectedBooking.getReference_id());
        infoTextView.setText("Booking changes are only allowed at least before 5 days before the reservation date");

        removeBookingButton.setEnabled(false);
        cancelBookingButton.setEnabled(false);
        updateBookingButton.setEnabled(false);

        if (selectedBooking.getStatus().equals("Active")) {
            updateBookingButton.setEnabled(false);
            if (Utils.isValidUpdate(selectedBooking.getReservation_date())) {
                cancelBookingButton.setEnabled(true);
                updateBookingButton.setEnabled(true);
            } else {
                cancelBookingButton.setEnabled(false);
                updateBookingButton.setEnabled(false);
            }
        } else {
            updateBookingButton.setEnabled(false);
            cancelBookingButton.setEnabled(false);
            updateBookingButton.setEnabled(false);
        }

        if (Utils.isDateInPast(selectedBooking.getReservation_date())) {
            removeBookingButton.setEnabled(true);
            cancelBookingButton.setEnabled(false);
            updateBookingButton.setEnabled(false);
        }

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5041/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiService interface
        ApiService apiService = retrofit.create(ApiService.class);

        // Make an API call to fetch reservations
        Call<TrainSchedule> call = apiService.getSchedule(selectedBooking.getTrain_id());

        call.enqueue(new Callback<TrainSchedule>() {
            @Override
            public void onResponse(Call<TrainSchedule> call, Response<TrainSchedule> response) {
                Log.i("TrainInfo", "After on response on update");
                if (response.isSuccessful() && response.body() != null) {
                    // Data retrieval was successful
                    selectedSchedule = response.body();
                    Log.i("TrainInfo", "Success Case: " + selectedSchedule.getTrain_name());
                    // Create ArrayAdapter for each Spinner
                    ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(UpdateBookingActivity.this, android.R.layout.simple_spinner_item, selectedSchedule.getIntermediate_stops());
                    ArrayAdapter<String> toAdapter = new ArrayAdapter<>(UpdateBookingActivity.this, android.R.layout.simple_spinner_item, selectedSchedule.getIntermediate_stops());
                    ArrayAdapter<String> classesAdapter = new ArrayAdapter<>(UpdateBookingActivity.this, android.R.layout.simple_spinner_item, selectedSchedule.getSeat_classes());

                    // Set the dropdown layout style for each Spinner
                    fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    classesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    fromSpinner.setAdapter(fromAdapter);
                    toSpinner.setAdapter(toAdapter);
                    classesSpinner.setAdapter(classesAdapter);

                    fromSpinner.setSelection(Utils.getPosition(selectedSchedule.getIntermediate_stops(), selectedBooking.getFrom()));
                    toSpinner.setSelection(Utils.getPosition(selectedSchedule.getIntermediate_stops(), selectedBooking.getTo()));
                    classesSpinner.setSelection(Utils.getPosition(selectedSchedule.getSeat_classes(), Utils.getTicketClassAsString(selectedBooking.getTicket_class())));

                } else {
                    Toast.makeText(UpdateBookingActivity.this, "Failed to load.", Toast.LENGTH_SHORT).show();
                    Log.e("TrainInfo", "Failed get bookings. HTTP error code: " + response.code());

                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("TrainInfo", "Error response body: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TrainSchedule> call, Throwable t) {
                Log.i("TrainInfo", "On failure: " + t.getMessage());
                Toast.makeText(UpdateBookingActivity.this, "Failed to load.", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle drop-down for available stations and the selected one
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedFrom = selectedSchedule.getIntermediate_stops().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedFrom = selectedBooking.getFrom();
            }
        });

        // Handle drop-down for available stations and the selected one
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedTo = selectedSchedule.getIntermediate_stops().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedTo = selectedBooking.getTo();
            }
        });

        // Handle drop-down for available classes and the selected one
        classesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedClass = selectedSchedule.getSeat_classes().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedClass = Utils.getTicketClassAsString(selectedBooking.getTicket_class());
            }
        });

        // Handle the "Update" button click event
        updateBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get updated values
                selectedBooking.setFrom(selectedFrom);
                selectedBooking.setTo(selectedTo);
                selectedBooking.setTicket_class(Utils.getTrainClassAsNumber(selectedClass));
                selectedBooking.setNumber_of_tickets(Integer.parseInt(noOfSeatsEditText.getText().toString()));
                selectedBooking.setReservation_date(expandCollapseButton.getText().toString());
                selectedBooking.setTotal_price(Utils.getTotal(Integer.parseInt(noOfSeatsEditText.getText().toString()), Utils.getTrainClassAsNumber(selectedClass)));

                String invalidData = Utils.validateData(selectedBooking, selectedSchedule);

                if(invalidData.equals("")) {
                    // Create an Intent to navigate to BookingActivity
                    Intent intent = new Intent(UpdateBookingActivity.this, BookingUpdateSummaryActivity.class);
                    // Pass the selected schedule data to BookingActivity
                    intent.putExtra("reservation", selectedBooking);
                    intent.putExtra("type", "update");
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateBookingActivity.this, invalidData, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle the "Cancel" button
        cancelBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get updated values
                selectedBooking.setStatus("Cancelled");
                // Create an Intent to navigate to BookingActivity
                Intent intent = new Intent(UpdateBookingActivity.this, BookingUpdateSummaryActivity.class);
                // Pass the selected schedule data to BookingActivity
                intent.putExtra("reservation", selectedBooking);
                intent.putExtra("type", "cancel");
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

    // Function to handle the date-picker validation and visibility
    public void toggleDatePickerVisibility(View view) {
        DatePicker datePicker = findViewById(R.id.updateBookingDatePicker);
        TextView expandCollapseButton = findViewById(R.id.updateBookingExpandCollapseButton);

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