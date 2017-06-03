package com.ariets.kata.ui.vending;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.ariets.kata.model.Coin;
import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.VendingMachine;

public class VendingMachinePresenter implements
        VendingMachineContract.Presenter<VendingMachineContract.View> {

    private final VendingMachine vendingMachine;
    private final DisplayProvider displayProvider;

    @VisibleForTesting
    @Nullable
    VendingMachineContract.View view;

    public VendingMachinePresenter(VendingMachine vendingMachine, DisplayProvider displayProvider) {
        this.vendingMachine = vendingMachine;
        this.displayProvider = displayProvider;
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
        if (view == null) {
            return;
        }
        vendingMachine.insertCoin(coin);
        view.setCurrentValue(vendingMachine.getFormattedCurrentValue());
    }

    @Override
    public String getInitialDisplay() {
        return displayProvider.displayInsertCoin();
    }
}
