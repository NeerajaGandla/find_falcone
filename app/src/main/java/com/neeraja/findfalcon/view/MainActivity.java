package com.neeraja.findfalcon.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private static final String TAG = "MainActivity";
    private Context mContext;

    @BindViews({R.id.sp_planet1_name, R.id.sp_planet2_name, R.id.sp_planet3_name, R.id.sp_planet4_name})
    List<Spinner> planetSpinners;

    @BindViews({R.id.sp_vehicle1_name, R.id.sp_vehicle2_name, R.id.sp_vehicle3_name, R.id.sp_vehicle4_name})
    List<Spinner> vehicleSpinners;

    @BindView(R.id.btn_find_falcone)
    Button findFalconBtn;

    @BindView(R.id.tv_time_taken1)
    TextView timeTaken1Tv;
    @BindView(R.id.tv_time_taken2)
    TextView timeTaken2Tv;
    @BindView(R.id.tv_time_taken3)
    TextView timeTaken3Tv;
    @BindView(R.id.tv_time_taken4)
    TextView timeTaken4Tv;

    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Planet> planets = new ArrayList<>();
    private ArrayAdapter<Vehicle> vehicleArrayAdapter;
    private ArrayAdapter<Planet> planetArrayAdapter;
    private String token;
    private String planet1Name, planet2Name, planet3Name, planet4Name,
            vehicle1Name, vehicle2Name, vehicle3Name, vehicle4Name;
    private Vehicle vehicle1, vehicle2, vehicle3, vehicle4;
    private Planet planet1, planet2, planet3, planet4;
    private double timeTaken1, timeTaken2, timeTaken3, timeTaken4;

    private FindFalconeRequest findFalconeRequest;

    private MainActivityPresenter mPresenter;
    ButterKnife.Action<Spinner> setPlanets;
    ButterKnife.Action<Spinner> setVehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ButterKnife.bind(this);
        mPresenter = new MainActivityPresenter(this);
        vehicleArrayAdapter = new ArrayAdapter<Vehicle>(mContext, android.R.layout.simple_list_item_1, vehicles);
        planetArrayAdapter = new ArrayAdapter<Planet>(mContext, android.R.layout.simple_dropdown_item_1line, planets);

        setVehicles = new ButterKnife.Action<Spinner>() {
            @Override
            public void apply(@NonNull Spinner spinner, int index) {
                Log.d(TAG, "apply: " + spinner.getId());
                spinner.setAdapter(vehicleArrayAdapter);
            }
        };
        ButterKnife.apply(vehicleSpinners, setVehicles);

        setPlanets = new ButterKnife.Action<Spinner>() {
            @Override
            public void apply(@NonNull Spinner spinner, int index) {
                Log.d(TAG, "apply: " + spinner.getId());
                spinner.setAdapter(planetArrayAdapter);
            }
        };
        ButterKnife.apply(planetSpinners, setPlanets);

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
            planet1 = (Planet) parent.getAdapter().getItem(pos);
            planet1Name = planet1.getName();
            if (vehicle1 != null) {
                double vehicleSpeed = vehicle1.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken1 = planet1.getDistance() / vehicleSpeed;
                    timeTaken1Tv.setText(String.valueOf(timeTaken1));
                }
            } else {
                timeTaken1Tv.setText(null);
            }
        } else if (parent.getId() == R.id.sp_planet2_name) {
            planet2 = (Planet) parent.getAdapter().getItem(pos);
            planet2Name = planet2.getName();
            if (vehicle2 != null) {
                double vehicleSpeed = vehicle2.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken2 = planet2.getDistance() / vehicleSpeed;
                    timeTaken2Tv.setText(String.valueOf(timeTaken2));
                }
            } else {
                timeTaken2Tv.setText(null);
            }
        } else if (parent.getId() == R.id.sp_planet3_name) {
            planet3 = (Planet) parent.getAdapter().getItem(pos);
            planet3Name = planet3.getName();
            if (vehicle3 != null) {
                double vehicleSpeed = vehicle3.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken3 = planet3.getDistance() / vehicleSpeed;
                    timeTaken3Tv.setText(String.valueOf(timeTaken3));
                }
            } else {
                timeTaken3Tv.setText(null);
            }
        } else if (parent.getId() == R.id.sp_planet4_name) {
            planet4 = (Planet) parent.getAdapter().getItem(pos);
            planet4Name = planet4.getName();
            if (vehicle4 != null) {
                double vehicleSpeed = vehicle4.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken4 = planet4.getDistance() / vehicleSpeed;
                    timeTaken4Tv.setText(String.valueOf(timeTaken4));
                }
            } else {
                timeTaken4Tv.setText(null);
            }
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
            vehicle1 = (Vehicle) parent.getAdapter().getItem(pos);
            vehicle1Name = vehicle1.getName();
            if (planet1 != null) {
                double vehicleSpeed = vehicle1.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken1 = planet1.getDistance() / vehicleSpeed;
                    timeTaken1Tv.setText(String.valueOf(timeTaken1));
                }
            } else {
                timeTaken1Tv.setText(null);
            }
        } else if (parent.getId() == R.id.sp_vehicle2_name) {
            vehicle2 = (Vehicle) parent.getAdapter().getItem(pos);
            vehicle2Name = vehicle2.getName();
            if (planet2 != null) {
                double vehicleSpeed = vehicle2.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken2 = planet2.getDistance() / vehicleSpeed;
                    timeTaken2Tv.setText(String.valueOf(timeTaken2));
                }
            } else {
                timeTaken2Tv.setText(null);
            }
        } else if (parent.getId() == R.id.sp_vehicle3_name) {
            vehicle3 = (Vehicle) parent.getAdapter().getItem(pos);
            vehicle3Name = vehicle3.getName();
            if (planet3 != null) {
                double vehicleSpeed = vehicle3.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken3 = planet3.getDistance() / vehicleSpeed;
                    timeTaken3Tv.setText(String.valueOf(timeTaken3));
                }
            } else {
                timeTaken3Tv.setText(null);
            }
        } else if (parent.getId() == R.id.sp_vehicle4_name) {
            vehicle4 = (Vehicle) parent.getAdapter().getItem(pos);
            vehicle4Name = vehicle4.getName();
            if (planet4 != null) {
                double vehicleSpeed = vehicle4.getSpeed();
                if (vehicleSpeed != 0) {
                    timeTaken4 = planet4.getDistance() / vehicleSpeed;
                    timeTaken4Tv.setText(String.valueOf(timeTaken4));
                }
            } else {
                timeTaken4Tv.setText(null);
            }
        }
    }

    @OnClick(R.id.btn_find_falcone)
    public void onClickFind(View view) {
        if (Utils.isValidString(planet1Name) && Utils.isValidString(planet2Name)
                && Utils.isValidString(planet3Name) && Utils.isValidString(planet4Name)
                && Utils.isValidString(vehicle1Name) && Utils.isValidString(vehicle2Name)
                && Utils.isValidString(vehicle3Name) && Utils.isValidString(vehicle4Name)) {
            if (Utils.getConnectivityStatus(mContext)) {
                mPresenter.getToken();
            } else {
                Utils.showAlert(getString(R.string.internet_connection_msg), mContext);
            }
        } else {
            Utils.showAlert(getString(R.string.select_all_planets_vehicles), mContext);
        }
    }

    @Override
    public void onLoadVehicles(List<Vehicle> vehicleList) {
        if (Utils.isValidArrayList((ArrayList<?>) vehicleList)) {
            vehicles = vehicleList;
            vehicleArrayAdapter = new ArrayAdapter<Vehicle>(mContext, android.R.layout.simple_list_item_1, vehicles);
            vehicleArrayAdapter.notifyDataSetChanged();
            ButterKnife.apply(vehicleSpinners, setVehicles);
        } else {
            Utils.showAlert(getString(R.string.no_vehicles_data), mContext);
        }
    }

    @Override
    public void onLoadPlanets(List<Planet> planetList) {
        if (Utils.isValidArrayList((ArrayList<?>) planetList)) {
            planets = planetList;
            planetArrayAdapter = new ArrayAdapter<Planet>(mContext, android.R.layout.simple_list_item_1, planets);
            planetArrayAdapter.notifyDataSetChanged();
            ButterKnife.apply(planetSpinners, setPlanets);
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
            String status = findFalconResponse.getStatus();
            if (!Utils.isValidString(status) || !status.equalsIgnoreCase("false")) {
                String error = findFalconResponse.getError();
                if (!Utils.isValidString(error)) {
                    intent.putExtra(getString(R.string.message), getString(R.string.success_msg));
                    String planetName = findFalconResponse.getPlanet_name();
                    if (Utils.isValidString(planetName)) {
                        double timeTaken = 0;
                        if (planetName.equalsIgnoreCase(planet1Name)) {
                            timeTaken = timeTaken1;
                        } else if (planetName.equalsIgnoreCase(planet1Name)) {
                            timeTaken = timeTaken2;
                        } else if (planetName.equalsIgnoreCase(planet1Name)) {
                            timeTaken = timeTaken3;
                        } else if (planetName.equalsIgnoreCase(planet1Name)) {
                            timeTaken = timeTaken4;
                        }
                        intent.putExtra(getString(R.string.time_taken), timeTaken);
                        intent.putExtra(getString(R.string.planet_name), planetName);
                    }
                } else {
                    intent.putExtra(getString(R.string.message), error);
                }
                startActivity(intent);
            } else {
                Utils.showAlert(getString(R.string.error_finding_falcone), mContext);
            }
        } else {
            Utils.showAlert(getString(R.string.no_data), mContext);
        }
    }
}