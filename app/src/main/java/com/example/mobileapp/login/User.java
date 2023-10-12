package com.example.mobileapp.login;

public class User {
    int id;
    String nic;
    String name;

    public User(int id, String nic, String name) {
        this.id = id;
        this.nic = nic;
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
