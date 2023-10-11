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
