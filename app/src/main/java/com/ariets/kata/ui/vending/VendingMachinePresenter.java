package com.ariets.kata.ui.vending;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.ariets.kata.model.Coin;
import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.Product;
import com.ariets.kata.model.VendingMachine;
import com.ariets.kata.model.VendingResult;

import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.CHIPS;
import static com.ariets.kata.model.Product.COLA;
import static com.ariets.kata.ui.vending.VendingError.INVALID_COIN;
import static com.ariets.kata.ui.vending.VendingError.NO_COINS;

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
        if (vendingMachine.insertCoin(coin)) {
            onSuccess();
        } else {
            onError(coin.getValue());
        }
    }

    @Override
    public void insertCustomValue(String insertedTextString) {
        try {
            double value = Double.valueOf(insertedTextString);
            if (vendingMachine.insertValue(value)) {
                onSuccess();
            } else {
                onError(value);
            }
        } catch (NumberFormatException exception) {
            view.onError(INVALID_COIN);
        }
    }

    @Override
    public void selectProduct(Product product) {
        VendingResult result = vendingMachine.selectProduct(product);
        switch (result) {
            case SUCCESS:
                view.setCurrentDisplay(displayProvider.displayThankYou());
                view.setCurrentValue(vendingMachine.getFormattedCurrentValue());
                view.onSuccess(product);
                break;
            case SOLD_OUT:
                view.setCurrentDisplay(displayProvider.displaySoldOut());
                view.onError(VendingError.PRODUCT_SOLD_OUT, product.toString());
                break;
            case INSUFFICIENT_FUNDS:
                view.setCurrentDisplay(displayProvider.displayPrice(product));
                view.onError(VendingError.INSUFFICIENT_FUNDS,
                        displayProvider.displayPrice(product.getPrice()));
                break;
            case EXACT_CHANGE_REQUIRED:
                view.setCurrentDisplay(displayProvider.displayExactChange());
                view.onError(VendingError.EXACT_CHANGE);
                break;
        }

    }

    @Override
    public void initialize() {
        double moneyInMachine = vendingMachine.getMoneyInMachine();
        String initialDisplay;
        if (moneyInMachine < CANDY.getPrice() || moneyInMachine < CHIPS.getPrice() || moneyInMachine < COLA.getPrice()) {
            initialDisplay = displayProvider.displayExactChange();
        } else {
            initialDisplay = displayProvider.displayInsertCoin();
        }
        view.setCurrentDisplay(initialDisplay);
        view.setCurrentValue(vendingMachine.getFormattedCurrentValue());
    }

    @Override
    public void getInitialValue() {
        view.setCurrentValue(vendingMachine.getFormattedCurrentValue());
    }

    @Override
    public void returnCoins() {
        double currentValue = vendingMachine.getCurrentValue();
        if (currentValue == 0) {
            view.onError(NO_COINS);
        } else {
            view.returnChange(vendingMachine.getFormattedCurrentValue());
            vendingMachine.returnCoins();
            view.setCurrentValue(vendingMachine.getFormattedCurrentValue());
        }
    }

    private void onSuccess() {
        view.setCurrentValue(vendingMachine.getFormattedCurrentValue());
    }

    private void onError(double value) {
        view.returnChange(displayProvider.displayPrice(value));
        view.onError(INVALID_COIN);
    }
}
