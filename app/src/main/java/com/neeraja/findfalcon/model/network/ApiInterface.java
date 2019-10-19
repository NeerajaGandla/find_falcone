package com.neeraja.findfalcon.model.network;

import com.neeraja.findfalcon.model.data.FindFalconResponse;
import com.neeraja.findfalcon.model.data.FindFalconeRequest;
import com.neeraja.findfalcon.model.data.Planet;
import com.neeraja.findfalcon.model.data.TokenResponse;
import com.neeraja.findfalcon.model.data.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("planets")
    Call<List<Planet>> getPlanets();

    @GET("vehicles")
    Call<List<Vehicle>> getVehicles();

    @Headers({"Accept: application/json"})
    @POST("token")
    Call<TokenResponse> getToken();

    @Headers({"Accept: application/json","Content-Type :application/json"})
    @POST("find")
    Call<FindFalconResponse> findFalcone(@Body FindFalconeRequest request);

 }
