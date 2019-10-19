package com.neeraja.findfalcon.contract;

import com.neeraja.findfalcon.model.data.FindFalconResponse;
import com.neeraja.findfalcon.model.data.FindFalconeRequest;
import com.neeraja.findfalcon.model.data.Planet;
import com.neeraja.findfalcon.model.data.TokenResponse;
import com.neeraja.findfalcon.model.data.Vehicle;

import java.util.List;

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
    }
}
