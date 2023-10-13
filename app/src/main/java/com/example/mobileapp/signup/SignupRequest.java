package com.example.mobileapp.signup;

public class SignupRequest {
    private String id;
    private String nic;
    private String fullName;
    private String contact;
    private String passwordHash;

    public SignupRequest(String id, String nic, String fullName, String contact, String passwordHash) {
        this.id = id;
        this.nic = nic;
        this.fullName = fullName;
        this.contact = contact;
        this.passwordHash = passwordHash;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
