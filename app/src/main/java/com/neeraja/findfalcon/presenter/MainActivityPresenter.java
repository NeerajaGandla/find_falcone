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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private static final String TAG = "MainActivityPresenter";

    MainActivityContract.View mView;
    ApiInterface apiService;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.mView = view;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public void loadVehicles() {
        Call<List<Vehicle>> call = apiService.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                int statusCode = response.code();
                List<Vehicle> vehicles = response.body();
                mView.onLoadVehicles(vehicles);
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                Log.e(TAG, t.toString());
                mView.onLoadVehicles(null);
            }
        });
    }

    @Override
    public void loadPlanets() {
        Call<List<Planet>> call = apiService.getPlanets();
        call.enqueue(new Callback<List<Planet>>() {
            @Override
            public void onResponse(Call<List<Planet>> call, Response<List<Planet>> response) {
                int statusCode = response.code();
                List<Planet> planets = response.body();
                mView.onLoadPlanets(planets);
            }

            @Override
            public void onFailure(Call<List<Planet>> call, Throwable t) {
                Log.e(TAG, t.toString());
                mView.onLoadPlanets(null);
            }
        });
    }

    @Override
    public void getToken() {
        Call<TokenResponse> call = apiService.getToken();
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                int statusCode = response.code();
                TokenResponse tokenResponse = response.body();
                mView.onGetToken(tokenResponse);
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                mView.onGetToken(null);
            }
        });
    }

    @Override
    public void findFalcone(FindFalconeRequest request) {
        Call<FindFalconResponse> call = apiService.findFalcone(request);
        call.enqueue(new Callback<FindFalconResponse>() {
            @Override
            public void onResponse(Call<FindFalconResponse> call, Response<FindFalconResponse> response) {
                int statusCode = response.code();
                FindFalconResponse findFalconResponse = response.body();
                mView.onFindFalcone(findFalconResponse);
            }

            @Override
            public void onFailure(Call<FindFalconResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                mView.onFindFalcone(null);
            }
        });
    }
}
