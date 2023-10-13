package com.example.mobileapp.login;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginServiceGenerator {
    private static final String BASE_URL = "http://10.0.2.2:5041/";

    public static LoginService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("LoginInfo", "LoginService created");
        return retrofit.create(LoginService.class);
    }
}
