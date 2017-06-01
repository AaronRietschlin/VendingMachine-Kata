package com.ariets.kata.model;

import java.util.List;

import static com.ariets.kata.model.VendingResult.INSUFFICIENT_FUNDS;
import static com.ariets.kata.model.VendingResult.SUCCESS;

public class VendingMachine {

    private final MoneyValidator moneyValidator;
    private final DisplayProvider displayProvider;

    private double currentValue;

    public VendingMachine(MoneyValidator moneyValidator, DisplayProvider displayProvider) {
        this.moneyValidator = moneyValidator;
        this.displayProvider = displayProvider;
    }

    public boolean insertCoin(Coin coin) {
        if (!moneyValidator.isValid(coin)) {
            return false;
        }
        currentValue += coin.getValue();
        return true;
    }

    public boolean insertValue(double value) {
        if (!moneyValidator.isValid(value)) {
            return false;
        }
        currentValue += value;
        return true;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public VendingResult selectProduct(Product product) {
        if (currentValue >= product.getPrice()) {
            // TOD0 - Remove from vault of products.
            return SUCCESS;
        }
        return INSUFFICIENT_FUNDS;
    }

}
