package com.ariets.kata.ui.vending;

import android.support.annotation.VisibleForTesting;

import com.ariets.kata.model.Coin;
import com.ariets.kata.model.VendingMachine;

public class VendingMachinePresenter implements VendingMachineContract.Presenter<VendingMachineContract.View> {

    private final VendingMachine vendingMachine;

    @VisibleForTesting
    VendingMachineContract.View view;

    public VendingMachinePresenter(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void attachView(VendingMachineContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.insertCoin(coin);
    }
}
