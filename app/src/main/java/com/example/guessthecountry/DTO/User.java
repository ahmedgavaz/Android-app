package com.example.guessthecountry.DTO;

public class User {
    private int id;
    private String username;
    private String password;

    public User() {
    }

    public User(String Username, String _pass) {
        this.username = Username;
        this.password = _pass;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String _pass) {
        this.password = _pass;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
