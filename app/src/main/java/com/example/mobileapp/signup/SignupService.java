/**
 * This file contains the implementation of the SignupService class, which is responsible for handling user sign up requests.
 */
package com.example.mobileapp.signup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignupService {
    @POST("/api/traveler")
    Call<SignupResponse> signup(@Body SignupRequest body);
}
