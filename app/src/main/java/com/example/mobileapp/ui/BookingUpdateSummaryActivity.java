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

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity class for displaying and confirming booking details updates or cancellations.
 */
public class BookingUpdateSummaryActivity extends AppCompatActivity {

    private TextView trainNameTextView, trainRouteTextView,
            reservationNumberTextView, refNoTextView,
            createdDateTextView, reservationDateTextView, classTextView,
            numberOfTicketsTextView, totalTextView, journeyTextView, confirmationText;
    private Button confirmButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_update_summary);

        Reservation reservation = getIntent().getParcelableExtra("reservation");
        String type = getIntent().getStringExtra("type");

        trainNameTextView = findViewById(R.id.summaryTrainNameTextView);
        trainRouteTextView = findViewById(R.id.summaryDepTextView);
        reservationNumberTextView = findViewById(R.id.summaryResNoTextView);
        refNoTextView = findViewById(R.id.summaryNICTextView);
        createdDateTextView = findViewById(R.id.summaryDatePlacedTextView);
        reservationDateTextView = findViewById(R.id.summaryReservationDateTextView);
        classTextView = findViewById(R.id.summaryClassTextView);
        numberOfTicketsTextView = findViewById(R.id.summaryTicketsTextView);
        totalTextView = findViewById(R.id.summaryTotalTextView);
        confirmButton = findViewById(R.id.summaryConfirmButton);
        backButton = findViewById(R.id.summaryBackButton);
        journeyTextView = findViewById(R.id.summaryJourneyTextView);
        confirmationText = findViewById(R.id.confirmationText);

        // set values
        trainNameTextView.setText(reservation.getTrain_name());
        trainRouteTextView.setText(reservation.getTravel_route());
        reservationNumberTextView.setText(reservation.getReservation_number());
        refNoTextView.setText(reservation.getReference_id());
        createdDateTextView.setText(reservation.getBooking_date());
        reservationDateTextView.setText(reservation.getReservation_date());
        classTextView.setText(String.valueOf(reservation.getTicket_class()));
        numberOfTicketsTextView.setText(String.valueOf(reservation.getNumber_of_tickets()));
        totalTextView.setText(Float.toString(reservation.getTotal_price()));
        journeyTextView.setText(reservation.getFrom() + " - " + reservation.getTo());

        if (type.equals("update")) {
            confirmationText.setText("Please check and confirm the changes");
        } else if (type.equals("cancel")) {
            confirmationText.setText("Once cancelled, you cannot re-activate the booking");
        } else {
            confirmationText.setText("Once removed, you cannot recover booking details");
        }
        backButton.setText("< Back");

        // back button to go back to the previous screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Handling confirmation button and make the API call
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


                if (type.equals("update") || type.equals("cancel")) {
                    // Make the PUT request
                    Call<Void> call = apiService.updateReservation(reservation.getId(), reservation);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                // Handle success
                                Toast.makeText(BookingUpdateSummaryActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(BookingUpdateSummaryActivity.this, HomeActivity.class);
                                startActivity(intent);

                            } else {
                                // Handle error
                                Toast.makeText(BookingUpdateSummaryActivity.this, "Failed to update reservation.", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(BookingUpdateSummaryActivity.this, "Failed to update the reservation.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Make the DELETE request
                    Call<Void> call = apiService.removeReservation(reservation.getId());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                // Handle success ]
                                Toast.makeText(BookingUpdateSummaryActivity.this, "Removed successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(BookingUpdateSummaryActivity.this, HomeActivity.class);
                                startActivity(intent);

                            } else {
                                // Handle error
                                Toast.makeText(BookingUpdateSummaryActivity.this, "Failed to remove reservation.", Toast.LENGTH_SHORT).show();
                                Log.e("Reservation", "Failed to remove reservation. HTTP error code: " + response.code());

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
                            Toast.makeText(BookingUpdateSummaryActivity.this, "Failed to remove the reservation.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}