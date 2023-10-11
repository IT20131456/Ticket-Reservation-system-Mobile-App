package com.example.mobileapp.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/Traveler/login")
    Call<LoginResponse> login(@Body LoginRequest body);
}
