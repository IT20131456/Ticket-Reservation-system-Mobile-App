/**
 * This file represents the SignupResponse class, which is responsible for handling the response received
 * from the server after a user signs up for the mobile application. It is located at the following file path:
 */
package com.example.mobileapp.signup;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {
    @SerializedName("statusCode")
    private String statusCode;

    public SignupResponse(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
