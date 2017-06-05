package com.ariets.kata.ui.vending;

import com.ariets.kata.model.VendingMachine;

/**
 * Represents an error that has occured within the {@link VendingMachine}. This tells the UI what
 * to display. Note: I went this route so I could keep the {@link VendingMachinePresenter} free
 * of any android code.
 */
public enum VendingError {

    NO_COINS,
    INVALID_COIN,
    PRODUCT_SOLD_OUT,
    INSUFFICIENT_FUNDS,
    EXACT_CHANGE

}
