/**
 * This file represents the SignupResponse class, which is responsible for handling the response received
 * from the server after a user signs up for the mobile application. It is located at the following file path:
 */
package com.example.mobileapp.signup;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {
    @SerializedName("StatusCode")
    private String StatusCode;

    public SignupResponse(String statusCode) {
        StatusCode = statusCode;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }
}
