package com.ariets.kata.model;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
import static com.ariets.kata.model.VendingResult.INSUFFICIENT_FUNDS;
import static com.ariets.kata.model.VendingResult.SOLD_OUT;
import static com.ariets.kata.model.VendingResult.SUCCESS;

public class VendingMachine {

    private final MoneyValidator moneyValidator;
    private final DisplayProvider displayProvider;
    private final ProductDispenser productDispenser;

    private double currentValue;

    public VendingMachine(MoneyValidator moneyValidator, DisplayProvider displayProvider,
                          ProductDispenser productDispenser) {
        this.moneyValidator = moneyValidator;
        this.displayProvider = displayProvider;
        this.productDispenser = productDispenser;
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

    @Nullable public String getFormattedCurrentValue() {
        if (currentValue < 0) {
            currentValue = 0;
        }
        return displayProvider.displayPrice(currentValue);
    }

    public VendingResult selectProduct(Product product) {
        double price = product.getPrice();
        if (currentValue >= price) {
            if (productDispenser.isAvailable(product)) {
                currentValue -= price;
                // TODO - Do I want to "dispense" product here? Or in the calling class. For now, keeping it here.
                productDispenser.dispenseItem(product);
                return SUCCESS;
            } else {
                return SOLD_OUT;
            }
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
