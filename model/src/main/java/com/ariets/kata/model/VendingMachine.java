package com.ariets.kata.model;

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
        increaseCurrentValue(coin);
        return true;
    }

    private void increaseCurrentValue(Coin coin) {
        currentValue += coin.getValue();
    }

    public double getCurrentValue() {
        return currentValue;
    }
}
