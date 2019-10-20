package com.neeraja.findfalcon.presenter;

import android.util.Log;

import com.neeraja.findfalcon.contract.MainActivityContract;
import com.neeraja.findfalcon.model.data.FindFalconResponse;
import com.neeraja.findfalcon.model.data.FindFalconeRequest;
import com.neeraja.findfalcon.model.data.Planet;
import com.neeraja.findfalcon.model.data.TokenResponse;
import com.neeraja.findfalcon.model.data.Vehicle;
import com.neeraja.findfalcon.model.network.ApiClient;
import com.neeraja.findfalcon.model.network.ApiInterface;
import com.neeraja.findfalcon.model.repository.MainActivityRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private static final String TAG = "MainActivityPresenter";

    MainActivityContract.View mView;
    MainActivityContract.Repository repository;
    ApiInterface apiService;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.mView = view;
        this.repository = new MainActivityRepo(this);
    }

    @Override
    public void loadVehicles() {
        repository.loadVehicles();
    }

    @Override
    public void loadPlanets() {
        repository.loadPlanets();
    }

    @Override
    public void getToken() {
        repository.getToken();
    }

    @Override
    public void findFalcone(FindFalconeRequest request) {
        repository.findFalcone(request);
    }

    @Override
    public void onHandleVehiclesResponse(Response<List<Vehicle>> vehiclesResponse) {
        if (vehiclesResponse != null) {
            int statusCode = vehiclesResponse.code();
            List<Vehicle> vehicles = vehiclesResponse.body();
            mView.onLoadVehicles(vehicles);
        } else {
            mView.onLoadVehicles(null);
        }
    }

    @Override
    public void onHandlePlanetsResponse(Response<List<Planet>> planetsResponse) {
        if (planetsResponse != null) {
            int statusCode = planetsResponse.code();
            List<Planet> planets = planetsResponse.body();
            mView.onLoadPlanets(planets);
        } else {
            mView.onLoadPlanets(null);
        }
    }

    @Override
    public void onHandleTokenResponse(Response<TokenResponse> response) {
        if (response != null) {
            int statusCode = response.code();
            TokenResponse tokenResponse = response.body();
            mView.onGetToken(tokenResponse);
        } else {
            mView.onGetToken(null);
        }
    }

    @Override
    public void onHandleFindFalconeResponse(Response<FindFalconResponse> response) {
        if (response != null) {
            int statusCode = response.code();
            FindFalconResponse falconResponse = response.body();
            mView.onFindFalcone(falconResponse);
        } else {
            mView.onFindFalcone(null);
        }
    }
}
