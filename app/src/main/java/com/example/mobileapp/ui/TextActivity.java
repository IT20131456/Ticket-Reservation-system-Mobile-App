package com.example.mobileapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mobileapp.R;

/**
 * Reference - https://www.youtube.com/watch?v=ixRXEoGAEZM
 */
public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        //Button viewUserProfileButton = findViewById(R.id.card_home_my_profile);
        CardView cardView = findViewById(R.id.card_home_my_profile);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to UserProfileActivity
                Intent intent = new Intent(TextActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}