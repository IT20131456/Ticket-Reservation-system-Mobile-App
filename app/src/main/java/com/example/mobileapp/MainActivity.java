package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapp.ui.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Button gotoLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoLoginBtn = findViewById(R.id.gotoLoginButton);

        gotoLoginBtn.setOnClickListener(v -> {
            // Create an Intent to navigate to AllSchedulesActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}