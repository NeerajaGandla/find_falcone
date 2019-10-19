package com.neeraja.findfalcon.model.data;

import com.google.gson.Gson;

import java.util.List;

public class FindFalconeRequest {
    private String token;
    private List<String> planet_names;
    private List<String> vehicle_names;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getPlanet_names() {
        return planet_names;
    }

    public void setPlanet_names(List<String> planet_names) {
        this.planet_names = planet_names;
    }

    public List<String> getVehicle_names() {
        return vehicle_names;
    }

    public void setVehicle_names(List<String> vehicle_names) {
        this.vehicle_names = vehicle_names;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
