package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.api.ApiService;
import com.example.mobileapp.data.model.Reservation;
import com.example.mobileapp.data.model.TrainSchedule;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity class for displaying and confirming train booking details, and making the final reservation.
 */
public class BookingConfirmationActivity extends AppCompatActivity {

    private TextView trainNameTextView, trainTypeTextView, durationTextView,
            trainFromTextView, trainToTextView, trainStropsTextView,
            trainClassesTextView, reservationNumberTextView, refNoTextView,
            createdDateTextView, reservationDateTextView, classTextView,
            numberOfTicketsTextView, totalTextView, journeyTextView;

    private Button confirmButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        Log.i("Reservation", "onCreate: BookingConfirmationActivity");
        // Retrieve schedule data passed from BookingActivity
        TrainSchedule selectedSchedule = getIntent().getParcelableExtra("schedule");
        Reservation reservation = getIntent().getParcelableExtra("reservation");

        Log.i("Reservation", "onCreate: After retrieving passed data");

        trainNameTextView = findViewById(R.id.bookingConTrainNameTextView);
        trainTypeTextView = findViewById(R.id.bookingConTypeTextView);
        durationTextView = findViewById(R.id.bookingConDurationTextView);
        trainFromTextView = findViewById(R.id.bookingConDepTextView);
        trainToTextView = findViewById(R.id.bookingConArrTextView);
        trainStropsTextView = findViewById(R.id.bookingConStopsTextView);
        trainClassesTextView = findViewById(R.id.bookingConClassesTextView);
        reservationNumberTextView = findViewById(R.id.bookingConResNoTextView);
        refNoTextView = findViewById(R.id.bookingConNICTextView);
        createdDateTextView = findViewById(R.id.bookingConDatePlacedTextView);
        reservationDateTextView = findViewById(R.id.bookingConReservationDateTextView);
        classTextView = findViewById(R.id.bookingConClassTextView);
        numberOfTicketsTextView = findViewById(R.id.bookingConTicketsTextView);
        totalTextView = findViewById(R.id.bookingConTotalTextView);
        confirmButton = findViewById(R.id.bookingConConfirmButton);
        backButton = findViewById(R.id.bookingConBackButton);
        journeyTextView = findViewById(R.id.bookingConJourneyTextView);

        // Set values
        trainNameTextView.setText(reservation.getTrain_name());
        trainTypeTextView.setText(selectedSchedule.getTrain_type());
        durationTextView.setText(selectedSchedule.getTravel_duration());
        trainFromTextView.setText(selectedSchedule.getDeparture_station() + ": " + selectedSchedule.getDeparture_time());
        trainToTextView.setText(selectedSchedule.getArrival_time() + ": " + selectedSchedule.getArrival_time());
        trainStropsTextView.setText(selectedSchedule.getIntermediate_stops().toString());
        trainClassesTextView.setText(selectedSchedule.getSeat_classes().toString());
        reservationNumberTextView.setText(reservation.getReservation_number());
        refNoTextView.setText(reservation.getReference_id());
        createdDateTextView.setText(reservation.getBooking_date());
        reservationDateTextView.setText(reservation.getReservation_date());
        classTextView.setText(String.valueOf(reservation.getTicket_class()));
        numberOfTicketsTextView.setText(String.valueOf(reservation.getNumber_of_tickets()));
        totalTextView.setText(Float.toString(reservation.getTotal_price()));
        journeyTextView.setText(reservation.getFrom() + " - " + reservation.getTo());

        backButton.setText("< Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Initialize Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:5041/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Create an instance of ApiService using Retrofit
                ApiService apiService = retrofit.create(ApiService.class);

                // Make the POST request
                Call<Void> call = apiService.bookTicket(reservation);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // Handle success
                            Toast.makeText(BookingConfirmationActivity.this, "Ticket booked successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BookingConfirmationActivity.this, HomeActivity.class);
                            startActivity(intent);

                        } else {
                            // Handle error
                            Toast.makeText(BookingConfirmationActivity.this, "Failed to book ticket.", Toast.LENGTH_SHORT).show();
                            Log.e("Reservation", "Failed to book ticket. HTTP error code: " + response.code());

                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("Reservation", "Error response body: " + errorBody);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Handle failure
                        Log.e("Reservation", "onFailure: " + t.getMessage());
                        Toast.makeText(BookingConfirmationActivity.this, "Failed to book ticket.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}