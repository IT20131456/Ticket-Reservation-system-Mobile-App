package com.example.mobileapp.userProfile;

import com.google.gson.annotations.SerializedName;

public class UserProfileCancelRequest {
    @SerializedName("StatusCode")
    private String StatusCode;

    public UserProfileCancelRequest(String statusCode) {
        StatusCode = statusCode;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }
}
