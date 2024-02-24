package com.example.guessthecountry;

public class Country {
    private String name;
    private String flag;
    private String map;
    private String capital;

    public Country(String name, String flag, String map, String capital) {
        this.name = name;
        this.flag = flag;
        this.map = map;
        this.capital = capital;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
