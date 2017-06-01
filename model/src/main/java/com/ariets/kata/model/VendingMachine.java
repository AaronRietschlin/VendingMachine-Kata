package com.ariets.kata.model;

public class VendingMachine {

    private final MoneyValidator moneyValidator;
    private final DisplayProvider displayProvider;

    public VendingMachine(MoneyValidator moneyValidator, DisplayProvider displayProvider) {
        this.moneyValidator = moneyValidator;
        this.displayProvider = displayProvider;
    }
}
