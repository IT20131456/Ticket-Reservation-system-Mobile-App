package com.example.mobileapp.userProfile;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class generates a Retrofit instance for the UserProfileService interface.
 */
public class UserProfileServiceGenerator {
    private static final String BASE_URL = "http://10.0.2.2:5041/";

    /**
     * Creates a new instance of the UserProfileService interface using Retrofit.
     *
     * @return A new instance of the UserProfileService interface.
     */
    public static UserProfileService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("UserProfileServiceInfo", "UserProfileService created");
        return retrofit.create(UserProfileService.class);
    }
}
