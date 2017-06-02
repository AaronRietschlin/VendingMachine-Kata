package com.ariets.kata.model;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
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
        double price = product.getPrice();
        if (currentValue >= price) {
            // TOD0 - Remove from vault of products.
            currentValue -= price;
            return SUCCESS;
        }
        return INSUFFICIENT_FUNDS;
    }

    // TODO - Fix the change precision issue. 
    @Nullable
    public List<Coin> getChange() {
        if (currentValue == 0) {
            return null;
        }
        List<Coin> change = new ArrayList<>();
        addCoinForOffset(change, QUARTER);
        addCoinForOffset(change, DIME);
        addCoinForOffset(change, NICKEL);
        addCoinForOffset(change, PENNY);
        return change;
    }

    private void addCoinForOffset(List<Coin> currentList, Coin coin) {
        double coinValue = coin.getValue();
        int coinCount = (int) (currentValue / coinValue);
        while (coinCount-- > 0) {
            currentList.add(coin);
            currentValue -= coinValue;
        }
    }

}
