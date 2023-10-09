package com.example.mobileapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.R;

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
