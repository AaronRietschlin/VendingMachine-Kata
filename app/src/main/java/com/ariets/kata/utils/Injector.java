package com.ariets.kata.utils;

import android.content.Context;

import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.MoneyValidator;
import com.ariets.kata.model.ProductDispenser;
import com.ariets.kata.model.VendingMachine;
import com.ariets.kata.ui.vending.VendingMachineContract;
import com.ariets.kata.ui.vending.VendingMachinePresenter;

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
        return new VendingMachine(validator, displayProvider, dispenser);
    }

    public MoneyValidator provideMoneyValidator() {
        return new MoneyValidator();
    }

    public DisplayProvider provideAndroidMoneyValidator(Context context) {
        return new AndroidDisplayProvider(context.getApplicationContext());
    }

    public ProductDispenser provideProductDispenser() {
        // TODO - Better way to do this? For now, defaulting to five of each.
        return new ProductDispenser(asList(
                CANDY, CANDY, CANDY, CANDY, CANDY,
                CHIPS, CHIPS, CHIPS, CHIPS, CHIPS,
                COLA, COLA, COLA, COLA, COLA
        ));
    }

}
