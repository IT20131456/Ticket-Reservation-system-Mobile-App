package com.example.mobileapp.signup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignupService {
    @POST("/api/traveler/login")
    Call<SignupResponse> login(@Body SignupRequest body);
}
