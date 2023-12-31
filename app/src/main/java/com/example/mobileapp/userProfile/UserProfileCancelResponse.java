package com.example.mobileapp.userProfile;

public class UserProfileCancelResponse {
    String id;
    String fullName;
    String nic;
    String contact;
    String email;
    String address;
    String accountStatus;
    String passwordHash;

    public UserProfileCancelResponse(String id, String fullName, String nic, String contact, String email, String address, String accountStatus, String passwordHash) {
        this.id = id;
        this.fullName = fullName;
        this.nic = nic;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.accountStatus = accountStatus;
        this.passwordHash = passwordHash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
