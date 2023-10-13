package com.example.mobileapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.R;
import com.example.mobileapp.signup.SignupRequest;
import com.example.mobileapp.signup.SignupResponse;
import com.example.mobileapp.signup.SignupServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * SignupActivity is the activity that allows users to sign up for an account.
 *
 * This activity displays a registration form with the required fields for creating a new account, such as NIC, full name, contact, and password.
 * When the user clicks the "Sign Up" button, the activity validates the user input and then creates a new account on the server.
 *
 * If the account creation is successful, the activity redirects the user to the LoginActivity. Otherwise, the activity displays an error message to the user.
 */

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button gotoHomeBtn;
        gotoHomeBtn = findViewById(R.id.registerButton);

        gotoHomeBtn.setOnClickListener(v -> {
            signup();
        });
    }

    private void signup() {
        // Get the user input from the EditText fields
        EditText userNICInputted = findViewById(R.id.signupNIC);
        EditText userFullNameInputted = findViewById(R.id.signupFullName);
        EditText userContactInputted = findViewById(R.id.signupContact);
        EditText userPasswordInputted = findViewById(R.id.signupPassword);
        EditText userConformPasswordInputted = findViewById(R.id.signupConformPassword);

        String userNICInput = userNICInputted.getText().toString();
        String userFullNameInput = userFullNameInputted.getText().toString();
        String userContactInput = userContactInputted.getText().toString();
        String userPasswordInput = userPasswordInputted.getText().toString();
        String userConformPasswordInput = userConformPasswordInputted.getText().toString();

        if (userNICInput.isEmpty() || userFullNameInput.isEmpty() || userContactInput.isEmpty() || userPasswordInput.isEmpty() || userConformPasswordInput.isEmpty() ) {
            Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a SignupRequest object
        SignupRequest signupRequest = new SignupRequest(null,userNICInput,userFullNameInput,userContactInput,userPasswordInput);

        // Call the login API
        SignupServiceGenerator.create().signup(signupRequest).enqueue(new Callback<SignupResponse>() {

            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {

                    // Signup successful
                    Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();

                    // Redirect to the LoginActivity
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Signup failed
                    Toast.makeText(SignupActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                // Login failed
                Toast.makeText(SignupActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                Log.i("LoginInfo", String.valueOf(t));
            }
        });
    }
}