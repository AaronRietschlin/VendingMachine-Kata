package com.ariets.kata.ui.vending;

import com.ariets.kata.model.Coin;
import com.ariets.kata.model.Product;

/**
 * The "contract" for the vending machine UI and presenter.
 */
public interface VendingMachineContract {

    /**
     * The UI for the vending machine.
     */
    interface View {

        /**
         * Sets the current value. Informs the users how much money they have inserted into the
         * vending machine.
         */
        void setCurrentValue(String currentValue);

        /**
         * Sets the amount of change the vending machine is returning to the user.
         */
        void returnChange(String change);

        /**
         * Gives the UI the product that was dispensed from the Vending Machine upon the criteria
         * being met.
         *
         * @param product - The product that was dispensed.
         */
        void onSuccess(Product product);

        /**
         * The method called when some sort of error has occurred.
         *
         * @param error  The actual error. See {@link VendingError}.
         * @param params Any additional string values that are relevant to the user. This could be
         *               the product name, the change value, etc.
         */
        void onError(VendingError error, String... params);

        /**
         * Sets the current state of the Vending Machine. This is something like "Insert Coins"
         */
        void setCurrentDisplay(String display);
    }

    /**
     * The presenter for the Vending machine. This is where the logic should live, outside of any
     * android code. This allows the UI to be dumb and only need to worry about UI and user
     * interactions.
     */
    interface Presenter<V extends View> {
        /**
         * Attach the given view.
         */
        void attachView(V view);

        /**
         * Detach the view. Due to possible memory leaks, the view should be null-ed out here.
         */
        void detachView();

        /**
         * Set up the presenter. This will give the {@link View} the initial state of the Vending
         * machine.
         */
        void initialize();

        /**
         * Performs the necessary logic of inserting a coin. If the coin is invalid,
         * {@link View#onError} will be called.
         *
         * @param coin The {@link Coin} that was inserted by the user.
         */
        void insertCoin(Coin coin);

        /**
         * Performs the necessary logic of inserting a custom value by the user.
         */
        void insertCustomValue(String insertedTextString);

        /**
         * Performs the necessary logic for the user selecting a {@link Product}.
         * @param product
         */
        void selectProduct(Product product);

        /**
         * Gets the initial amount of money that the vending machine currently has.
         */
        void getInitialValue();

        /**
         * Performs the logic for the user clicking the coin return.
         */
        void returnCoins();
    }

}
