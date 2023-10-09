package com.example.mobileapp.ui;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.mobileapp.R;
import com.example.mobileapp.adapter.ScheduleAdapter;
import com.example.mobileapp.api.ApiService;
import com.example.mobileapp.data.model.TrainSchedule;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllSchedulesActivity extends AppCompatActivity {

    private ListView scheduleListView;
    private ScheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_schedules);

        scheduleListView = findViewById(R.id.scheduleListView);

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
                    // Data retrieval was successful
                    Log.i("TrainInfo", "Success Case");
                    List<TrainSchedule> scheduleList = response.body();
                    adapter = new ScheduleAdapter(AllSchedulesActivity.this, R.layout.list_item_schedule, scheduleList);
                    scheduleListView.setAdapter(adapter);

                } else {
                    Log.i("TrainInfo", "Failed Case");
                    // Handle error if the API call was not successful
                    // You can display an error message or take appropriate action here
                }
            }

            @Override
            public void onFailure(Call<List<TrainSchedule>> call, Throwable t) {
                Log.i("TrainInfo", "On failure: " + t.getMessage());
                // Handle failure to make the API call
                // You can display an error message or take appropriate action here
            }
        });
    }
}

//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//
//import com.example.mobileapp.R;
//import com.example.mobileapp.adapter.ScheduleAdapter;
//import com.example.mobileapp.data.model.TrainSchedule;
//
//import java.util.ArrayList;
//
//public class AllSchedulesActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all_schedules);
//
//        ListView scheduleListView = findViewById(R.id.scheduleListView);
//        Button backButton = findViewById(R.id.schedulesBackButton);
//
//        // Create a list of available schedules (temporary data)
////        TODO: replace this with the actual values retrieved from the web service
//        ArrayList<TrainSchedule> scheduleList = new ArrayList<>();
//        // Add temporary schedule data to the list
//        scheduleList.add(new TrainSchedule());
//        scheduleList.add(new TrainSchedule());
//        scheduleList.add(new TrainSchedule("start", "dest", "Udarata Menike", "8.00 AM", "15.00 PM", 100, "1, 2", "st1, st2, st3, st4"));
//
//        ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.list_item_schedule, scheduleList);
//        scheduleListView.setAdapter(adapter);
//
//        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Get the selected TrainSchedule
//                TrainSchedule selectedSchedule = scheduleList.get(position);
//
//                // Create an Intent to navigate to ScheduleActivity
//                Intent intent = new Intent(AllSchedulesActivity.this, ScheduleDetailsActivity.class);
//                // Pass selected schedule data to ScheduleActivity
//                intent.putExtra("schedule", selectedSchedule);
//                startActivity(intent);
//            }
//        });
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//}