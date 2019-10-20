package com.neeraja.findfalcon.contract;

import com.neeraja.findfalcon.model.data.FindFalconResponse;
import com.neeraja.findfalcon.model.data.FindFalconeRequest;
import com.neeraja.findfalcon.model.data.Planet;
import com.neeraja.findfalcon.model.data.TokenResponse;
import com.neeraja.findfalcon.model.data.Vehicle;

import java.util.List;

import retrofit2.Response;

public class MainActivityContract {
    public interface View {
        void onLoadVehicles(List<Vehicle> vehicleList);
        void onLoadPlanets(List<Planet> planets);
        void onGetToken(TokenResponse response);
        void onFindFalcone(FindFalconResponse response);
    }
    public interface Presenter {
        void loadVehicles();
        void loadPlanets();
        void getToken();
        void findFalcone(FindFalconeRequest request);
        void onHandleVehiclesResponse(Response<List<Vehicle>> vehiclesResponse);
        void onHandlePlanetsResponse(Response<List<Planet>> planetsResponse);
        void onHandleTokenResponse(Response<TokenResponse> response);
        void onHandleFindFalconeResponse(Response<FindFalconResponse> response);
    }
    public interface Repository {
        void loadVehicles();
        void loadPlanets();
        void getToken();
        void findFalcone(FindFalconeRequest request);
    }
}
