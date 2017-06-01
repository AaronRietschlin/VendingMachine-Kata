package com.ariets.kata.model;

import static com.ariets.kata.model.Coin.PENNY;

public class MoneyValidator {

    public boolean isValid(Coin coin) {
        return coin != PENNY;
    }

    public boolean isCorrectPayment(double currentPayment, Product product) {
        return false;
    }

}
