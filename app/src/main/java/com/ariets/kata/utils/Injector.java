package com.ariets.kata.utils;

import android.content.Context;

import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.MoneyValidator;
import com.ariets.kata.model.Product;
import com.ariets.kata.model.ProductDispenser;
import com.ariets.kata.model.VendingMachine;
import com.ariets.kata.ui.vending.VendingMachineContract;
import com.ariets.kata.ui.vending.VendingMachinePresenter;

import java.util.HashMap;
import java.util.Map;

import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.CHIPS;
import static com.ariets.kata.model.Product.COLA;
import static java.util.Arrays.asList;

public class Injector {

    public VendingMachineContract.Presenter<VendingMachineContract.View> providePresenter(
            VendingMachine vendingMachine, DisplayProvider displayProvider) {
        return new VendingMachinePresenter(vendingMachine, displayProvider);
    }

    public VendingMachine provideVendingMachine(
            MoneyValidator validator, DisplayProvider displayProvider, ProductDispenser dispenser) {
        return new VendingMachine(validator, displayProvider, dispenser, 0.00);
    }

    public MoneyValidator provideMoneyValidator() {
        return new MoneyValidator();
    }

    public DisplayProvider provideAndroidMoneyValidator(Context context) {
        return new AndroidDisplayProvider(context.getApplicationContext());
    }

    public ProductDispenser provideProductDispenser() {
        Map<Product, Integer> map = new HashMap<>();
        map.put(CANDY, 5);
        map.put(CHIPS, 5);
        map.put(COLA, 5);
        return new ProductDispenser(map);
    }

}
