package com.example.guessthecountry.DTO;

public class Country {
    private String name;
    private String flag;
    private String map;
    private String capital;
    private String continent;
    private String level;

    public String getContinent() {
        return continent;
    }

    public Country(String name, String flag, String map, String capital, String continent, String level) {
        this.name = name;
        this.flag = flag;
        this.map = map;
        this.capital = capital;
        this.continent = continent;
        this.level = level;
    }

    public Country() {

    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
