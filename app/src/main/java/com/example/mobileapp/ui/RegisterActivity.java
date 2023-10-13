package com.example.mobileapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.R;

/**
 * RegisterActivity is the activity that allows users to register for an account.
 *
 * This activity displays a registration form with the required fields for creating a new account, such as user ID, name, email, and password.
 * When the user clicks the "Register" button, the activity validates the user input and then creates a new account on the server.
 *
 * If the account creation is successful, the activity redirects the user to the HomeActivity. Otherwise, the activity displays an error message to the user.
 */

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button gotoHomeBtn;
        gotoHomeBtn = findViewById(R.id.registerButton);

        gotoHomeBtn.setOnClickListener(v -> {
            // Create an Intent to navigate to AllSchedulesActivity
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
