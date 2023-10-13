/**
 * This file contains the definition of the LoginResponse class, which is used to represent the response
 * received from the server after attempting to log in.
 */
package com.example.mobileapp.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Data data;

    // Constructors, getters, setters, etc.

    public static class Data {
        @SerializedName("id")
        private String id;

        @SerializedName("nic")
        private String nic;

        @SerializedName("fullName")
        private String fullName;

        @SerializedName("dob")
        private String dob;

        @SerializedName("gender")
        private String gender;

        @SerializedName("contact")
        private String contact;

        @SerializedName("email")
        private String email;

        @SerializedName("address")
        private String address;

        @SerializedName("username")
        private String username;

        @SerializedName("passwordHash")
        private String passwordHash;

        @SerializedName("profile")
        private String profile; // If profile is an object, you can use a more specific type

        @SerializedName("travelerType")
        private String travelerType;

        @SerializedName("accountStatus")
        private String accountStatus;

        @SerializedName("createdAt")
        private String createdAt;

        public Data(String id, String nic, String fullName, String dob, String gender, String contact, String email, String address, String username, String passwordHash, String profile, String travelerType, String accountStatus, String createdAt) {
            this.id = id;
            this.nic = nic;
            this.fullName = fullName;
            this.dob = dob;
            this.gender = gender;
            this.contact = contact;
            this.email = email;
            this.address = address;
            this.username = username;
            this.passwordHash = passwordHash;
            this.profile = profile;
            this.travelerType = travelerType;
            this.accountStatus = accountStatus;
            this.createdAt = createdAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNic() {
            return nic;
        }

        public void setNic(String nic) {
            this.nic = nic;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getTravelerType() {
            return travelerType;
        }

        public void setTravelerType(String travelerType) {
            this.travelerType = travelerType;
        }

        public String getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public LoginResponse(String message, Data data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
