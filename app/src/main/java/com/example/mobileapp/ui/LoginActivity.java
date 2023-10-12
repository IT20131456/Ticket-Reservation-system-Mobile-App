package com.example.mobileapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.R;
import com.example.mobileapp.login.LoginRequest;
import com.example.mobileapp.login.LoginResponse;
import com.example.mobileapp.login.LoginServiceGenerator;
import com.example.mobileapp.login.SessionManagement;
import com.example.mobileapp.login.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    protected void onStart(){
        super.onStart();

        checkSession();
    }

    private void checkSession(){
        // Check if user is logged in
        // If user is logged in move to the HomeActivity
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userID = sessionManagement.getSession();

        if (userID != -1){
            // User already logged in and move to HomeActivity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            // User need to login
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button gotoHomeBtn;
        gotoHomeBtn = findViewById(R.id.loginButton);

//        gotoHomeBtn.setOnClickListener(v -> {
//            // Create an Intent to navigate to AllSchedulesActivity
//            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//            startActivity(intent);
//        });
        gotoHomeBtn.setOnClickListener(v -> {
            login();
        });
    }

    private void login() {
        // Get the user input from the EditText fields
        EditText userIDInputted = findViewById(R.id.userIDInput);
        EditText userPasswordInputted = findViewById(R.id.userPasswordInput);

        String userIDInput = userIDInputted.getText().toString();
        String userPasswordInput = userPasswordInputted.getText().toString();

        //String userIDInput = "759065724V";
        //String userPasswordInput = "password";

        // Validate the user input
        if (userIDInput.isEmpty() || userPasswordInput.isEmpty()) {
            Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a LoginRequest object
        LoginRequest loginRequest = new LoginRequest(userIDInput, userPasswordInput);

        // Call the login API
        LoginServiceGenerator.create().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    String fullName = response.body().getData().getFullName();
                    String nic = response.body().getData().getNic();

                    // Create user session
                    User user = new User(10224828,nic, fullName);

                    SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                    sessionManagement.saveSession(user);

                    // Login successful
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // Redirect to the HomeActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Login failed
                    Log.e("LoginInfo", "Failed login. HTTP error code: " + response.code());
                    // log the error response body
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("LoginInfo", "Error response body: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Login failed
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                Log.i("LoginInfo", String.valueOf(t));
            }
        });
    }
}