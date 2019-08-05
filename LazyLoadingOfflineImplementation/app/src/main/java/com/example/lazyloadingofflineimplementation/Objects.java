package com.example.lazyloadingofflineimplementation;

public class Objects {

    private String type;
    private String name;
    private String random;
    private String time;

    public Objects(String type, String name, String random, String time) {
        this.type = type;
        this.name = name;
        this.random = random;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
