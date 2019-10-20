package com.neeraja.findfalcon.model.repository;

import android.util.Log;

import com.neeraja.findfalcon.contract.MainActivityContract;
import com.neeraja.findfalcon.model.data.FindFalconResponse;
import com.neeraja.findfalcon.model.data.FindFalconeRequest;
import com.neeraja.findfalcon.model.data.Planet;
import com.neeraja.findfalcon.model.data.TokenResponse;
import com.neeraja.findfalcon.model.data.Vehicle;
import com.neeraja.findfalcon.model.network.ApiClient;
import com.neeraja.findfalcon.model.network.ApiInterface;
import com.neeraja.findfalcon.presenter.MainActivityPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityRepo implements MainActivityContract.Repository {
    private static final String TAG = "MainActivityRepo";
    MainActivityPresenter mPresenter;
    ApiInterface apiService;

    public MainActivityRepo(MainActivityPresenter presenter) {
        this.mPresenter = presenter;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void loadVehicles() {
        Call<List<Vehicle>> call = apiService.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                mPresenter.onHandleVehiclesResponse(response);
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                Log.e(TAG, t.toString());
                mPresenter.onHandleVehiclesResponse(null);
            }
        });
    }

    @Override
    public void loadPlanets() {
        Call<List<Planet>> call = apiService.getPlanets();
        call.enqueue(new Callback<List<Planet>>() {
            @Override
            public void onResponse(Call<List<Planet>> call, Response<List<Planet>> response) {
                mPresenter.onHandlePlanetsResponse(response);
            }

            @Override
            public void onFailure(Call<List<Planet>> call, Throwable t) {
                Log.e(TAG, t.toString());
                mPresenter.onHandlePlanetsResponse(null);
            }
        });
    }

    @Override
    public void getToken() {
        Call<TokenResponse> call = apiService.getToken();
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                mPresenter.onHandleTokenResponse(response);
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                mPresenter.onHandleTokenResponse(null);
            }
        });
    }

    @Override
    public void findFalcone(FindFalconeRequest request) {
        Call<FindFalconResponse> call = apiService.findFalcone(request);
        call.enqueue(new Callback<FindFalconResponse>() {

            @Override
            public void onResponse(Call<FindFalconResponse> call, Response<FindFalconResponse> response) {
                mPresenter.onHandleFindFalconeResponse(response);
            }

            @Override
            public void onFailure(Call<FindFalconResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                mPresenter.onHandleFindFalconeResponse(null);
            }
        });
    }
}
