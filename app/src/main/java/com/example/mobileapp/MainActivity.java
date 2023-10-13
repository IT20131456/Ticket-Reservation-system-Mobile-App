package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mobileapp.ui.LoginActivity;
import com.example.mobileapp.ui.SignupActivity;

public class MainActivity extends AppCompatActivity {

    Button gotoLoginBtn;
    Button gotoRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoLoginBtn = findViewById(R.id.mainLoginButton);
        gotoRegisterBtn = findViewById(R.id.mainRegisterButton);

        gotoLoginBtn.setOnClickListener(v -> {
            // Create an Intent to navigate to LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        gotoRegisterBtn.setOnClickListener(v -> {
            //  Create an Intent to navigate to SignupActivity
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}