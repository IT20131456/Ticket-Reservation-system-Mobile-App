/**
 * This file contains the SignupRequest class, which is responsible for handling
 * the signup request made by the user in the mobile app.
 */
package com.example.mobileapp.signup;

public class SignupRequest {
    private String Id;
    private String NIC;
    private String FullName;
    private String Contact;
    private String PasswordHash;
    private String accountStatus;

    public SignupRequest(String id, String NIC, String fullName, String contact, String passwordHash, String accountStatus) {
        Id = id;
        this.NIC = NIC;
        FullName = fullName;
        Contact = contact;
        PasswordHash = passwordHash;
        this.accountStatus = accountStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
