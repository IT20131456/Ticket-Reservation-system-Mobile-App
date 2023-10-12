package com.example.mobileapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.mobileapp.R;
import com.example.mobileapp.adapter.BookingAdapter;
import com.example.mobileapp.api.ApiService;
import com.example.mobileapp.data.model.Reservation;
import com.example.mobileapp.login.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllBookingsActivity extends AppCompatActivity {
    private ListView bookingListView;
    private SwitchCompat toggleButton;
    private EditText searchBar;
    private TextView testingLabel;
    private Button backButton;
    private BookingAdapter bookingAdapter;
    private List<Reservation> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bookings);

        backButton = findViewById(R.id.allBookingsBackButton);
        testingLabel = findViewById(R.id.TestingLabel);
        bookingListView = findViewById(R.id.bookingListView);
        toggleButton = findViewById(R.id.switchCompat);
        searchBar = findViewById(R.id.searchBar);

        backButton.setText("< Back");

        SessionManagement sessionManagement = new SessionManagement(AllBookingsActivity.this);
        String nic = sessionManagement.getSessionNIC();

        Log.i("TrainInfo", "onCreate: AllBookings");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5041/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiService interface
        ApiService apiService = retrofit.create(ApiService.class);
        Log.i("TrainInfo", "Before call");

        // Make an API call to fetch reservations
        Call<List<Reservation>> call = apiService.getAllBookings(nic); // Replace with your API call
        Log.i("TrainInfo", "After call");
        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                Log.i("TrainInfo", "After on response");
                if (response.isSuccessful() && response.body() != null) {
                    // Data retrieval was successful
                    Log.i("TrainInfo", "Success Case");
                    bookingList = response.body();
                    bookingAdapter = new BookingAdapter(AllBookingsActivity.this, R.layout.booking_card, bookingList);
                    bookingListView.setAdapter(bookingAdapter);

                    bookingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Get the selected booking
                            Reservation selectedBooking = bookingList.get(position);

                            // Create an Intent to navigate to booking update activity
                            Intent intent = new Intent(AllBookingsActivity.this, UpdateBookingActivity.class);
                            // Pass selected reservation
                            intent.putExtra("reservation", selectedBooking);
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(AllBookingsActivity.this, "Failed to load.", Toast.LENGTH_SHORT).show();
                    Log.e("TrainInfo", "Failed get bookings. HTTP error code: " + response.code());

                    // You can also log the error response body if needed
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("TrainInfo", "Error response body: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Log.i("TrainInfo", "On failure: " + t.getMessage());
                // Handle failure to make the API call
                // display an error message or take appropriate action here
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show only active bookings
                    filterActiveBookings();
                } else {
                    // Show all bookings
                    bookingAdapter = new BookingAdapter(AllBookingsActivity.this, R.layout.booking_card, bookingList);
                    bookingListView.setAdapter(bookingAdapter);
                }
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Handle before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Handle text changes and perform search
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Handle after text changes
            }
        });
    }

    private void filterActiveBookings() {
        List<Reservation> activeBookings = new ArrayList<>();
        for (Reservation reservation : bookingList) {
            if ("Active".equalsIgnoreCase(reservation.getStatus())) {
                activeBookings.add(reservation);
            }
        }
        bookingAdapter = new BookingAdapter(AllBookingsActivity.this, R.layout.booking_card, activeBookings);
        bookingListView.setAdapter(bookingAdapter);
    }

    private void performSearch(String query) {
        List<Reservation> searchResults = new ArrayList<>();
        for (Reservation reservation : bookingList) {
            if (reservation.getReservation_number().toLowerCase().contains(query.toLowerCase())
                    || reservation.getFrom().toLowerCase().contains(query.toLowerCase())
                    || reservation.getTo().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(reservation);
            }
        }
        bookingAdapter = new BookingAdapter(AllBookingsActivity.this, R.layout.booking_card, searchResults);
        bookingListView.setAdapter(bookingAdapter);
    }
}
