/**
 * This class represents the response received after updating a user's profile.
 */
package com.example.mobileapp.userProfile;

import com.google.gson.annotations.SerializedName;

public class UserProfileUpdateResponse {
    @SerializedName("message")
    private String message;

    public UserProfileUpdateResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
