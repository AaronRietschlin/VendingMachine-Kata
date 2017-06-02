package com.ariets.kata.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ariets.kata.R;
import com.ariets.kata.ui.widget.CoinView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @OnClick({
            R.id.coin_view_quarter,
            R.id.coin_view_dime,
            R.id.coin_view_nickel,
            R.id.coin_view_penny
    })
    public void onClick(CoinView view) {

    }
}
