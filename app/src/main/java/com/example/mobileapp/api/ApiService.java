package com.example.mobileapp.api;

import com.example.mobileapp.data.model.Reservation;
import com.example.mobileapp.data.model.TrainSchedule;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/TrainSchedule")
    Call<List<TrainSchedule>> getAllSchedules();

    @POST("api/TicketBooking")
    Call<Void> bookTicket(@Body Reservation reservation);

    @GET("api/TicketBooking/History/{nic}")
    Call<List<Reservation>> getAllBookings(@Path("nic") String id);
}

