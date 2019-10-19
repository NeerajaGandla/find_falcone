package com.neeraja.findfalcon.model.data;

import com.google.gson.Gson;

public class Planet {
    private String name;
    private int distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
