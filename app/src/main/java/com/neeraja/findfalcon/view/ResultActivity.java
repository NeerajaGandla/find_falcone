package com.neeraja.findfalcon.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.neeraja.findfalcon.R;

import butterknife.BindView;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.tv_result)
    TextView resultTv;
    @BindView(R.id.tv_planet_name)
    TextView planetNameTv;
    @BindView(R.id.btn_search_again)
    TextView searchAgainBtn;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mContext = ResultActivity.this;

        if (getIntent().getExtras() != null) {

        }
    }
}
