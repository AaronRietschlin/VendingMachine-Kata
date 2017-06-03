package com.ariets.kata.ui.vending;

import com.ariets.kata.model.Coin;

public interface VendingMachineContract {

    interface View {

        void setCurrentValue(String currentValue);

        void returnChange(String change);

        void onError(VendingError error);
    }

    interface Presenter<View> {
        void attachView(View view);

        void detachView();

        void insertCoin(Coin coin);

        void insertCustomValue(String insertedTextString);

        String getInitialDisplay();

        void getInitialValue();

        void returnCoins();
    }

}
