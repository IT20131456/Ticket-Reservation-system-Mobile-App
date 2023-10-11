package com.example.mobileapp.signup;

public class SignupRequest {
    private String Id;
    private String NIC;
    private String Contact;
    private String Email;
    private String PasswordHash;

    public SignupRequest(String id, String NIC, String contact, String email, String passwordHash) {
        Id = id;
        this.NIC = NIC;
        Contact = contact;
        Email = email;
        PasswordHash = passwordHash;
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

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }
}
