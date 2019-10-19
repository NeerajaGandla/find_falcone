package com.neeraja.findfalcon.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.neeraja.findfalcon.R;
import com.neeraja.findfalcon.contract.ResultActivityContract;
import com.neeraja.findfalcon.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity implements ResultActivityContract.View {

    @BindView(R.id.tv_result)
    TextView resultTv;
    @BindView(R.id.tv_planet_name)
    TextView planetNameTv;
    @BindView(R.id.btn_search_again)
    Button searchAgainBtn;
    @BindView(R.id.tv_time_taken)
    TextView timeTakenTv;

    private Context mContext;
    private String message, planetName;
    private double timeTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mContext = ResultActivity.this;
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            message = getIntent().getStringExtra(getString(R.string.message));
            planetName = getIntent().getStringExtra(getString(R.string.planet_name));
            timeTaken = getIntent().getDoubleExtra(getString(R.string.time_taken), 0);
            onLoad(message, planetName);
        }
    }

    @OnClick(R.id.btn_search_again)
    public void onClickStartAgain(View view) {
        onClickStart();
    }

    @Override
    public void onLoad(String message, String planetName) {
        if (Utils.isValidString(message)) {
            resultTv.setText(message);
        }
        if (Utils.isValidString(planetName)) {
            planetNameTv.setText("Planet : " + planetName);
            timeTakenTv.setText("Time Taken : " + String.valueOf(timeTaken));
            searchAgainBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickStart() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
