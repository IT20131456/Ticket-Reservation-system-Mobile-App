package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mobileapp.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button gotoHomeBtn;
        gotoHomeBtn = findViewById(R.id.registerButton);

        gotoHomeBtn.setOnClickListener(v -> {
            // Create an Intent to navigate to AllSchedulesActivity
            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}