package com.neeraja.findfalcon.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.neeraja.findfalcon.R;
import com.neeraja.findfalcon.contract.MainActivityContract;
import com.neeraja.findfalcon.model.data.FindFalconResponse;
import com.neeraja.findfalcon.model.data.FindFalconeRequest;
import com.neeraja.findfalcon.model.data.Planet;
import com.neeraja.findfalcon.model.data.TokenResponse;
import com.neeraja.findfalcon.model.data.Vehicle;
import com.neeraja.findfalcon.presenter.MainActivityPresenter;
import com.neeraja.findfalcon.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private Context mContext;

    @BindViews({R.id.sp_planet1_name, R.id.sp_planet2_name, R.id.sp_planet3_name, R.id.sp_planet4_name})
    List<AppCompatSpinner> planetSpinners;

    @BindViews({R.id.sp_vehicle1_name, R.id.sp_vehicle2_name, R.id.sp_vehicle3_name, R.id.sp_vehicle4_name})
    List<AppCompatSpinner> vehicleSpinners;

    @BindView(R.id.btn_find_falcone)
    Button findFalconBtn;

    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Planet> planets = new ArrayList<>();
    private ArrayAdapter<Vehicle> vehicleArrayAdapter;
    private ArrayAdapter<Planet> planetArrayAdapter;
    private String token;
    private String planet1Name, planet2Name, planet3Name, planet4Name,
            vehicle1Name, vehicle2Name, vehicle3Name, vehicle4Name;

    private FindFalconeRequest findFalconeRequest;

    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        mPresenter = new MainActivityPresenter(this);
        vehicleArrayAdapter = new ArrayAdapter<Vehicle>(mContext, android.R.layout.simple_list_item_1, vehicles);
        planetArrayAdapter = new ArrayAdapter<Planet>(mContext, android.R.layout.simple_dropdown_item_1line, planets);

        for (AppCompatSpinner spinner : vehicleSpinners) {
            spinner.setAdapter(vehicleArrayAdapter);
        }

        for (AppCompatSpinner spinner : planetSpinners) {
            spinner.setAdapter(planetArrayAdapter);
        }

        if (Utils.getConnectivityStatus(mContext)) {
            mPresenter.loadPlanets();
            mPresenter.loadVehicles();
        } else {
            Utils.showAlert(getString(R.string.internet_connection_msg), mContext);
        }
    }

    @OnItemSelected({R.id.sp_planet1_name, R.id.sp_planet2_name, R.id.sp_planet3_name, R.id.sp_planet4_name})
    public void onPlanetSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getId() == R.id.sp_planet1_name) {
            planet1Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        } else if (parent.getId() == R.id.sp_planet2_name) {
            planet2Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        } else if (parent.getId() == R.id.sp_planet3_name) {
            planet3Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        } else if (parent.getId() == R.id.sp_planet4_name) {
            planet4Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        }
    }

    @OnItemSelected(value = {R.id.sp_planet1_name, R.id.sp_planet2_name, R.id.sp_planet3_name, R.id.sp_planet4_name,
            R.id.sp_vehicle1_name, R.id.sp_vehicle2_name, R.id.sp_vehicle3_name, R.id.sp_vehicle4_name},
            callback = OnItemSelected.Callback.NOTHING_SELECTED)
    void onNothingSelected() {
        //do nothing
    }

    @OnItemSelected({R.id.sp_vehicle1_name, R.id.sp_vehicle2_name, R.id.sp_vehicle3_name, R.id.sp_vehicle4_name})
    public void onVehicleSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getId() == R.id.sp_vehicle1_name) {
            vehicle1Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        } else if (parent.getId() == R.id.sp_vehicle2_name) {
            vehicle2Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        } else if (parent.getId() == R.id.sp_vehicle3_name) {
            vehicle3Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        } else if (parent.getId() == R.id.sp_vehicle4_name) {
            vehicle4Name = ((Planet)parent.getAdapter().getItem(pos)).getName();
        }
    }

    @OnClick(R.id.btn_find_falcone)
    public void onClickFind(View view) {
        if (Utils.getConnectivityStatus(mContext)) {
            if (Utils.isValidString(planet1Name) && Utils.isValidString(planet2Name)
                    && Utils.isValidString(planet3Name) && Utils.isValidString(planet4Name)
            && Utils.isValidString(vehicle1Name) && Utils.isValidString(vehicle2Name)
            && Utils.isValidString(vehicle3Name) && Utils.isValidString(vehicle4Name)) {
                mPresenter.getToken();
            } else {
                Utils.showAlert(getString(R.string.select_all_planets_vehicles), mContext);
            }
        } else {
            Utils.showAlert(getString(R.string.internet_connection_msg), mContext);
        }
    }

    @Override
    public void onLoadVehicles(List<Vehicle> vehicleList) {
        if (Utils.isValidArrayList((ArrayList<?>) vehicleList)) {
            vehicles = vehicleList;
            vehicleArrayAdapter.notifyDataSetChanged();
        } else {
            Utils.showAlert(getString(R.string.no_vehicles_data), mContext);
        }
    }

    @Override
    public void onLoadPlanets(List<Planet> planetList) {
        if (Utils.isValidArrayList((ArrayList<?>) planetList)) {
            planets = planetList;
            planetArrayAdapter.notifyDataSetChanged();
        } else {
            Utils.showAlert(getString(R.string.no_planets_data), mContext);
        }
    }

    @Override
    public void onGetToken(TokenResponse response) {
        if (response != null) {
            token = response.getToken();
            if (Utils.isValidString(token)) {
                if (Utils.getConnectivityStatus(mContext)) {
                    findFalconeRequest = initializeFalconeModel();
                    mPresenter.findFalcone(findFalconeRequest);
                } else {
                    Utils.showAlert(getString(R.string.internet_connection_msg), mContext);
                }
            } else {
                Utils.showAlert(getString(R.string.token_generation_failed), mContext);
            }
        } else {
            Utils.showAlert(getString(R.string.token_generation_failed), mContext);
        }
    }

    private FindFalconeRequest initializeFalconeModel() {
        FindFalconeRequest request = new FindFalconeRequest();
        request.setToken(token);

        List<String> planetList = new ArrayList<>();
        planetList.add(planet1Name);
        planetList.add(planet2Name);
        planetList.add(planet3Name);
        planetList.add(planet4Name);

        List<String> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1Name);
        vehicleList.add(vehicle2Name);
        vehicleList.add(vehicle3Name);
        vehicleList.add(vehicle4Name);

        request.setPlanet_names(planetList);
        request.setVehicle_names(vehicleList);

        return request;
    }

    @Override
    public void onFindFalcone(FindFalconResponse findFalconResponse) {
        if (findFalconResponse != null) {
            Intent intent = new Intent(mContext, ResultActivity.class);
            String error = findFalconResponse.getError();
            if (!Utils.isValidString(error)) {
                intent.putExtra(getString(R.string.message), R.string.success_msg);
                String planetName = findFalconResponse.getPlanet_name();
                if (Utils.isValidString(planetName))
                    intent.putExtra(getString(R.string.planet_name), planetName);
            } else {
                intent.putExtra(getString(R.string.message), error);
            }
            startActivity(intent);
        } else {
            Utils.showAlert(getString(R.string.no_data), mContext);
        }
    }
}