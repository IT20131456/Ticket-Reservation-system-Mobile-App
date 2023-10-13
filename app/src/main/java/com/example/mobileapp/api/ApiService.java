package com.example.mobileapp.api;

import com.example.mobileapp.data.model.Reservation;
import com.example.mobileapp.data.model.TrainSchedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Interface for defining API endpoints and corresponding HTTP methods.
 */
public interface ApiService {

    /**
     * Get a list of all train schedules .
     */
    @GET("api/TrainSchedule")
    Call<List<TrainSchedule>> getAllSchedules();

    /**
     * Book a train ticket by sending a Reservation object in the request body.
     */
    @POST("api/TicketBooking")
    Call<Void> bookTicket(@Body Reservation reservation);

    /**
     * Get the booking history for a user based on their NIC.
     */
    @GET("api/TicketBooking/History/{nic}")
    Call<List<Reservation>> getAllBookings(@Path("nic") String id);

    /**
     * Update a reservation with the specified ID by sending a Reservation object in the request body.
     */
    @PUT("api/TicketBooking/{id}")
    Call<Void> updateReservation(@Path("id") String id, @Body Reservation reservation);

    /**
     * Remove a reservation with the specified ID.
     */
    @DELETE("api/TicketBooking/{id}")
    Call<Void> removeReservation(@Path("id") String id);

    /**
     * Get details of a specific train schedule by its train number.
     */
    @GET("api/TrainSchedule/tno/{id}")
    Call<TrainSchedule> getSchedule(@Path("id") String id);
}
