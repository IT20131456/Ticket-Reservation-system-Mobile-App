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

/**
 * This activity allows the user to view, filter, and search through their train reservations.
 * It retrieves the user's reservations from the API, displays them in a list view, and provides
 * options to filter active reservations and search for specific reservations.
 */
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

        Log.i("TrainInfo", "onCreate: AllBookings");

        backButton = findViewById(R.id.allBookingsBackButton);
        testingLabel = findViewById(R.id.TestingLabel);
        bookingListView = findViewById(R.id.bookingListView);
        toggleButton = findViewById(R.id.switchCompat);
        searchBar = findViewById(R.id.searchBar);

        backButton.setText("< Back");

        // Session management to retrieve user NIC
        SessionManagement sessionManagement = new SessionManagement(AllBookingsActivity.this);
        String nic = sessionManagement.getSessionNIC();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize Retrofit for API calls
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5041/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiService interface
        ApiService apiService = retrofit.create(ApiService.class);

        // Make an API call to fetch reservations
        Call<List<Reservation>> call = apiService.getAllBookings(nic); // Replace with your API call
        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful() && response.body() != null) {
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
                Log.e("TrainInfo", "Failed get bookings.");
            }
        });

        // Handle filtering active bookings
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

        // Handle the search operations
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

    /**
     * Filters the list of reservations to display only active bookings.
     */
    private void filterActiveBookings() {
        List<Reservation> activeBookings = new ArrayList<>();

        // Iterate through the bookingList and add active bookings to activeBookings list
        for (Reservation reservation : bookingList) {
            if ("Active".equalsIgnoreCase(reservation.getStatus())) {
                activeBookings.add(reservation);
            }
        }

        // Update the bookingAdapter to display the filtered active bookings
        bookingAdapter = new BookingAdapter(AllBookingsActivity.this, R.layout.booking_card, activeBookings);
        bookingListView.setAdapter(bookingAdapter);
    }

    /**
     * Performs a search on reservations based on a user-provided query and updates the display accordingly.
     *
     * @param query The search query entered by the user.
     */
    private void performSearch(String query) {
        List<Reservation> searchResults = new ArrayList<>();

        // Iterate through the bookingList and add reservations matching the query to searchResults
        for (Reservation reservation : bookingList) {
            if (reservation.getReservation_number().toLowerCase().contains(query.toLowerCase())
                    || reservation.getFrom().toLowerCase().contains(query.toLowerCase())
                    || reservation.getTo().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(reservation);
            }
        }

        // Update the bookingAdapter to display the search results
        bookingAdapter = new BookingAdapter(AllBookingsActivity.this, R.layout.booking_card, searchResults);
        bookingListView.setAdapter(bookingAdapter);
    }
}
