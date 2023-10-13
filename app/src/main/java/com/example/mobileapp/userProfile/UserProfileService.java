package com.example.mobileapp.userProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserProfileService {

    @GET("/api/traveler/nic/{nic}")
    Call<UserProfileResponse> retrieveUserProfileInfoByNIC(@Path("nic") String nic);

    @PUT("/api/traveler/nic/{nic}")
    Call<UserProfileUpdateResponse> updateUserProfileInfoByNIC(@Path("nic") String nic, @Body UserProfileUpdateRequest userProfileUpdateRequest);
}
