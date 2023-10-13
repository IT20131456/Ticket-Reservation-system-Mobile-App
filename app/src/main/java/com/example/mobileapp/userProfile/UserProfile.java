package com.example.mobileapp.userProfile;

/**
 * UserProfile represents a user profile in the system.
 *
 * This class contains all of the information about a user, such as their ID, full name, NIC, contact, email, address, account status, and password hash.
 *
 * The UserProfile class can be used to create, read, update, and delete user profiles. It can also be used to authenticate users and authorize their access to the system.
 */

public class UserProfile {
    String id;
    String fullName;
    String nic;
    String contact;
    String email;
    String address;
    String accountStatus;
    String passwordHash;

    public UserProfile(String id, String fullName, String nic, String contact, String email, String address, String accountStatus, String passwordHash) {
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
