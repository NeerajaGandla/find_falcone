package com.neeraja.findfalcon.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.Button;

import com.neeraja.findfalcon.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @BindView(R.id.sp_planet1_name)
    AppCompatSpinner planet1Sp;
    @BindView(R.id.sp_planet2_name)
    AppCompatSpinner planet2Sp;
    @BindView(R.id.sp_planet3_name)
    AppCompatSpinner planet3Sp;
    @BindView(R.id.sp_planet4_name)
    AppCompatSpinner planet4Sp;
    @BindView(R.id.sp_vehicle1_name)
    AppCompatSpinner vehicle1Sp;
    @BindView(R.id.sp_vehicle2_name)
    AppCompatSpinner vehicle2Sp;
    @BindView(R.id.sp_vehicle3_name)
    AppCompatSpinner vehicle3Sp;
    @BindView(R.id.sp_vehicle4_name)
    AppCompatSpinner vehicle4Sp;
    @BindView(R.id.btn_find_falcone)
    Button findFalconBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;


    }
}