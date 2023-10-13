package com.example.mobileapp.userProfile;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileServiceGenerator {
    private static final String BASE_URL = "http://192.168.8.164:5041/";

    public static UserProfileService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("UserProfileServiceInfo", "UserProfileService created");
        return retrofit.create(UserProfileService.class);
    }
}
