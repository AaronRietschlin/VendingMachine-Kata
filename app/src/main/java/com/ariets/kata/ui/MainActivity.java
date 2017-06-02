package com.ariets.kata.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ariets.kata.R;
import com.ariets.kata.ui.widget.CoinView;
import com.ariets.kata.ui.widget.ProductTextView;

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
    public void onCoinClicked(CoinView view) {
        Snackbar.make(toolbar, "Coin selected: " + view.getCoin(), Snackbar.LENGTH_LONG).show();
    }

    @OnClick({
            R.id.vending_product_candy,
            R.id.vending_product_chips,
            R.id.vending_product_cola,
    })
    public void onProductClicked(ProductTextView view) {
        Snackbar.make(toolbar, "Product selected: " + view.getProduct(), Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.vending_btn_custom_value) public void onCustomValueButtonClicked() {

    }
}
