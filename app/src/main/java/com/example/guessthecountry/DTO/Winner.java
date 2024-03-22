package com.example.guessthecountry.DTO;

public class Winner {
    private int id;
    private String username;
    private int points;

    public Winner(String username, int points) {
        this.username = username;
        this.points = points;
    }

    public Winner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
