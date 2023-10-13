package com.example.mobileapp.userProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * UserProfileService is a Retrofit service interface that provides methods for retrieving and updating user profile information.
 *
 * The UserProfileService can be used to make API calls to the server to retrieve or update a user's profile information.
 */

public interface UserProfileService {

    @GET("/api/traveler/nic/{nic}")
    Call<UserProfileResponse> retrieveUserProfileInfoByNIC(@Path("nic") String nic);

    @PUT("/api/traveler/nic/{nic}")
    Call<UserProfileUpdateResponse> updateUserProfileInfoByNIC(@Path("nic") String nic, @Body UserProfileUpdateRequest userProfileUpdateRequest);
}
