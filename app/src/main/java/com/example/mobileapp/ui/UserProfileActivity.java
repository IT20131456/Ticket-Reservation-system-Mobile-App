package com.example.mobileapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.R;
import com.example.mobileapp.login.SessionManagement;
import com.example.mobileapp.userProfile.UserProfileResponse;
import com.example.mobileapp.userProfile.UserProfileService;
import com.example.mobileapp.userProfile.UserProfileServiceGenerator;
import com.example.mobileapp.userProfile.UserProfileUpdateRequest;
import com.example.mobileapp.userProfile.UserProfileUpdateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * UserProfileActivity is the activity that allows users to view and update their profile information.
 *
 * This activity displays the user's profile information, such as NIC, full name, contact, email, and address.
 * The user can update their profile information by entering the new information in the EditText fields and clicking the "Update Profile" button.
 *
 * When the user clicks the "Update Profile" button, the activity validates the user input and then updates the user's profile information on the server.
 * If the profile information update is successful, the activity displays a success message to the user and redirects them to the HomeActivity.
 * Otherwise, the activity displays an error message to the user.
 */

public class UserProfileActivity extends AppCompatActivity {

    private String userObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button updateUserProfileButton = findViewById(R.id.btn_update_user_profile);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button cancelUserProfileButton = findViewById(R.id.btn_user_cancel);

        retrieveUserData();

        updateUserProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
            }
        });

        cancelUserProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelUserProfile();
            }
        });
    }

    private void cancelUserProfile() {

    }


    private void retrieveUserData() {
    // Get the user input NIC from the Session
        //String nic = "759065724V";
        SessionManagement sessionManagement = new SessionManagement(UserProfileActivity.this);
        String nic = sessionManagement.getSessionNIC();


        // Call the UserProfileService to retrieve the user profile information by NIC
        UserProfileService userProfileService = UserProfileServiceGenerator.create();
        Call<UserProfileResponse> userProfileResponseCall = userProfileService.retrieveUserProfileInfoByNIC(nic);

        userProfileResponseCall.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful()) {
                    // User profile information retrieved successfully
                    UserProfileResponse userProfileResponse = response.body();

                    // Get user object id
                    //String id = userProfileResponse.getId();
                    userObjectId = userProfileResponse.getId();

                    // Display the user profile information in the layout
                    EditText setUserNIC = findViewById(R.id.user_nic);
                    setUserNIC.setText(userProfileResponse.getNic());

                    EditText setUserFullName = findViewById(R.id.user_full_name);
                    setUserFullName.setText(userProfileResponse.getFullName());

                    EditText setUserContactNo = findViewById(R.id.user_contact_no);
                    setUserContactNo.setText(userProfileResponse.getContact());

                    EditText setUserEmail = findViewById(R.id.user_email);
                    setUserEmail.setText(userProfileResponse.getEmail());

                    EditText setUserAddress = findViewById(R.id.user_address);
                    setUserAddress.setText(userProfileResponse.getAddress());
                } else {
                    // User profile information retrieval failed
                    Toast.makeText(UserProfileActivity.this, "Failed to retrieve user profile information", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                // User profile information retrieval failed
                Toast.makeText(UserProfileActivity.this, "Failed to retrieve user profile information", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateUserData(){
        // Get user object id
        String id = userObjectId;

        // Get the user input NIC from the EditText field
        EditText setUserNIC = findViewById(R.id.user_nic);
        String nic = setUserNIC.getText().toString();

        // Get the user input update information from the EditText fields
        EditText setUserFullName = findViewById(R.id.user_full_name);
        String fullName = setUserFullName.getText().toString();

        EditText setUserContactNo = findViewById(R.id.user_contact_no);
        String contact = setUserContactNo.getText().toString();

        EditText setUserEmail = findViewById(R.id.user_email);
        String email = setUserEmail.getText().toString();

        EditText setUserAddress = findViewById(R.id.user_address);
        String address = setUserAddress.getText().toString();

        // Create a UserProfileUpdateRequest object
        UserProfileUpdateRequest userProfileUpdateRequest = new UserProfileUpdateRequest(id, fullName, nic, contact, email, address, "Active");

//        userProfileUpdateRequest.setFullName(fullName);
//        userProfileUpdateRequest.setContact(contact);
//        userProfileUpdateRequest.setEmail(email);
//        userProfileUpdateRequest.setAddress(address);

        // Call the UserProfileService to update the user profile information
        UserProfileService userProfileService = UserProfileServiceGenerator.create();
        Call<UserProfileUpdateResponse> userProfileUpdateResponseCall = userProfileService.updateUserProfileInfoByNIC(nic, userProfileUpdateRequest);

        userProfileUpdateResponseCall.enqueue(new Callback<UserProfileUpdateResponse>() {
            @Override
            public void onResponse(Call<UserProfileUpdateResponse> call, Response<UserProfileUpdateResponse> response) {
                if (response.isSuccessful()) {
                    // User profile information updated successfully
                    Toast.makeText(UserProfileActivity.this, "User profile information updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // User profile information update failed
                    Toast.makeText(UserProfileActivity.this, "Failed to update user profile information", Toast.LENGTH_SHORT).show();
                }
                // Move to UserProfileActivity
                Intent intent = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserProfileUpdateResponse> call, Throwable t) {
                // User profile information update failed
                Toast.makeText(UserProfileActivity.this, "Failed to update user profile information", Toast.LENGTH_SHORT).show();
                // Move to UserProfileActivity
                Intent intent = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}