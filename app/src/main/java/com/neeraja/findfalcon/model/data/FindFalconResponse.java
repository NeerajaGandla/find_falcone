package com.neeraja.findfalcon.model.data;

import com.google.gson.Gson;

public class FindFalconResponse {
    private String status;
    private String error;
    private String planet_name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPlanet_name() {
        return planet_name;
    }

    public void setPlanet_name(String planet_name) {
        this.planet_name = planet_name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
