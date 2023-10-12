package com.example.mobileapp.signup;

import android.util.Log;

import com.example.mobileapp.login.LoginService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupServiceGenerator {
    private static final String BASE_URL = "http://192.168.8.164:5041/";

    public static SignupService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("LoginInfo", "SignupService created");
        return retrofit.create(SignupService.class);
    }
}
