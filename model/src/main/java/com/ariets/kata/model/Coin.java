package com.ariets.kata.model;

/**
 * Represents the coins that are available to the user. Note: {@link #PENNY} is invalid.
 */
public enum Coin {

    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
