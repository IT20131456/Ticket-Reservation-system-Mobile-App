/**
 * This package contains classes related to the signup functionality of the mobile application.
 */
package com.example.mobileapp.signup;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupServiceGenerator {
    private static final String BASE_URL = "http://10.0.2.2:5041/";

    public static SignupService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("SignupInfo", "SignupService created");
        return retrofit.create(SignupService.class);
    }
}
