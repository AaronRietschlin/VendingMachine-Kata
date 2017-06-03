package com.ariets.kata.model;

import android.support.annotation.Nullable;

import static com.ariets.kata.model.VendingResult.EXACT_CHANGE_REQUIRED;
import static com.ariets.kata.model.VendingResult.INSUFFICIENT_FUNDS;
import static com.ariets.kata.model.VendingResult.SOLD_OUT;
import static com.ariets.kata.model.VendingResult.SUCCESS;

public class VendingMachine {

    private final MoneyValidator moneyValidator;
    private final DisplayProvider displayProvider;
    private final ProductDispenser productDispenser;

    private double currentValue;
    private double moneyInMachine;

    public VendingMachine(MoneyValidator moneyValidator, DisplayProvider displayProvider,
                          ProductDispenser productDispenser, double moneyInMachine) {
        this.moneyValidator = moneyValidator;
        this.displayProvider = displayProvider;
        this.productDispenser = productDispenser;
        this.moneyInMachine = moneyInMachine;
    }

    public boolean insertCoin(Coin coin) {
        if (!moneyValidator.isValid(coin)) {
            return false;
        }
        increaseValue(coin.getValue());
        return true;
    }

    public boolean insertValue(double value) {
        if (!moneyValidator.isValid(value)) {
            return false;
        }
        increaseValue(value);
        return true;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void returnCoins() {
        currentValue = 0;
        moneyInMachine -= currentValue;
    }

    @Nullable
    public String getFormattedCurrentValue() {
        if (currentValue < 0) {
            currentValue = 0;
        }
        return displayProvider.displayPrice(currentValue);
    }

    public VendingResult selectProduct(Product product) {
        double price = product.getPrice();
        if (currentValue >= price) {
            if (!canProvideChange(product)) {
                return EXACT_CHANGE_REQUIRED;
            } else if (productDispenser.isAvailable(product)) {
                decreaseValue(price);
                productDispenser.dispenseItem(product);
                return SUCCESS;
            } else {
                return SOLD_OUT;
            }
        }
        return INSUFFICIENT_FUNDS;
    }

    public double getMoneyInMachine() {
        return moneyInMachine;
    }

    // TODO - This isn't right. Need to determine the type of change we can make and return from that.
    // Do it by not increasing money in machine.
    public boolean canProvideChange(Product product) {
        return moneyInMachine > product.getPrice();
    }

    private void increaseValue(double money) {
        currentValue += money;
    }

    private void decreaseValue(double money) {
        currentValue -= money;
        moneyInMachine -= money;
    }

}
