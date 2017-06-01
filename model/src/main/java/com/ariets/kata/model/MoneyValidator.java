package com.ariets.kata.model;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.QUARTER;

public class MoneyValidator {

    // Because we want to allow the user to insert any coin (via input), we must check value of VALID coins.
    public boolean isValid(Coin coin) {
        return isValid(coin.getValue());
    }

    public boolean isValid(double value) {
        return value == DIME.getValue() || value == QUARTER.getValue() || value == NICKEL.getValue();
    }

    public boolean isCorrectPayment(double currentPayment, Product product) {
        return currentPayment >= product.getPrice();
    }

}
