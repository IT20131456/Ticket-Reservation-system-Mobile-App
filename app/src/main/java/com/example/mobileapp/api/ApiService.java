package com.example.mobileapp.api;

import com.example.mobileapp.data.model.TrainSchedule;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/TrainSchedule")
    Call<List<TrainSchedule>> getAllSchedules();
}

