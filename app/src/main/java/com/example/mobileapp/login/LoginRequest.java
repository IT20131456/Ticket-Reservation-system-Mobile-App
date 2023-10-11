package com.example.mobileapp.login;

public class LoginRequest {
    private String Id;
    private String Password;

    public LoginRequest(String userIDInput, String userPasswordInput) {
        this.Id = userIDInput;
        this.Password = userPasswordInput;
    }

    public String getUserIDInput() {
        return Id;
    }

    public String getUserPasswordInput() {
        return Password;
    }
}
