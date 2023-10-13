package com.example.mobileapp.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.adapter.ScheduleAdapter;
import com.example.mobileapp.api.ApiService;
import com.example.mobileapp.data.model.TrainSchedule;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity class for displaying a list of train schedules and enabling search functionality.
 */
public class AllSchedulesActivity extends AppCompatActivity {

    private ListView scheduleListView;
    private ScheduleAdapter adapter;
    private SearchView schedulesSearchView;
    private List<TrainSchedule> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_schedules);

        scheduleListView = findViewById(R.id.scheduleListView);
        Button backButton = findViewById(R.id.schedulesBackButton);
        schedulesSearchView = findViewById(R.id.schedulesSearchView);

        backButton.setText("< Back");

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5041/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiService interface
        ApiService apiService = retrofit.create(ApiService.class);

        // Make an API call to fetch schedules
        Call<List<TrainSchedule>> call = apiService.getAllSchedules();
        Log.i("TrainInfo", "Before call");
        call.enqueue(new Callback<List<TrainSchedule>>() {
            @Override
            public void onResponse(Call<List<TrainSchedule>> call, Response<List<TrainSchedule>> response) {
                Log.i("TrainInfo", "After on response");
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("TrainInfo", "Success Case");
                    scheduleList = response.body();
                    adapter = new ScheduleAdapter(AllSchedulesActivity.this, R.layout.list_item_schedule, scheduleList);
                    scheduleListView.setAdapter(adapter);

                    scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Get the selected TrainSchedule
                            TrainSchedule selectedSchedule = scheduleList.get(position);

                            // Create an Intent to navigate to ScheduleActivity
                            Intent intent = new Intent(AllSchedulesActivity.this, ScheduleDetailsActivity.class);
                            // Pass selected schedule data to ScheduleActivity
                            intent.putExtra("schedule", selectedSchedule);
                            startActivity(intent);
                        }
                    });

                } else {
                    Log.i("TrainInfo", "Failed Case");
                    Toast.makeText(AllSchedulesActivity.this, "Failed to load.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TrainSchedule>> call, Throwable t) {
                Log.i("TrainInfo", "On failure: " + t.getMessage());
                Toast.makeText(AllSchedulesActivity.this, "Failed to load.", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Handle search view
        schedulesSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the list based on the user's input
                if (adapter != null) {
                    performSearch(newText);
                }
                return true;
            }
        });
    }

    // Method to perform the search
    private void performSearch(String query) {
        List<TrainSchedule> searchResults = new ArrayList<>();
        for (TrainSchedule schedule : scheduleList) {
            if (schedule.getDeparture_station().toLowerCase().contains(query.toLowerCase())
                    || schedule.getArrival_station().toLowerCase().contains(query.toLowerCase())
                    || schedule.getTrain_name().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(schedule);
            }
        }
        adapter = new ScheduleAdapter(AllSchedulesActivity.this, R.layout.list_item_schedule, searchResults);
        scheduleListView.setAdapter(adapter);
    }
}