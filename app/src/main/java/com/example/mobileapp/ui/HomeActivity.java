package com.example.mobileapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.MainActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.login.SessionManagement;

/**
* Reference - https://www.youtube.com/watch?v=ixRXEoGAEZM
*/
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button viewAllSchedulesButton = findViewById(R.id.viewAllSchedulesButton);
        Button viewAllBookingsButton = findViewById(R.id.viewAllBookingsButton);
        Button viewUserProfileButton = findViewById(R.id.btn_my_profile);

        TextView loggedUserTextView = findViewById(R.id.logged_user);
        SessionManagement sessionManagement = new SessionManagement(HomeActivity.this);
        String userName = sessionManagement.getSessionName();
        String userNIC = sessionManagement.getSessionNIC();
        loggedUserTextView.setText("Hello " + userName + " NIC " + userNIC);


        // Set an OnClickListener for the button
        viewAllSchedulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to AllSchedulesActivity
                Intent intent = new Intent(HomeActivity.this, AllSchedulesActivity.class);
                startActivity(intent);
            }
        });

        viewAllBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to AllSchedulesActivity
                Intent intent = new Intent(HomeActivity.this, AllBookingsActivity.class);
                startActivity(intent);
            }
        });

        viewUserProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to UserProfileActivity
                Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    public void logout(View view) {
        // Remove user session and open login screen
        SessionManagement sessionManagement = new SessionManagement(HomeActivity.this);
        sessionManagement.removeSession();

        moveToMainActivity();
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}