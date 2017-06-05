package com.ariets.kata.ui.vending;

import com.ariets.kata.model.Coin;
import com.ariets.kata.model.Product;

public interface VendingMachineContract {

    interface View {

        void setCurrentValue(String currentValue);

        void returnChange(String change);

        void onSuccess(Product product);

        void onError(VendingError error, String... params);

        void setCurrentDisplay(String display);
    }

    interface Presenter<View> {
        void attachView(View view);

        void detachView();

        void initialize();

        void insertCoin(Coin coin);

        void insertCustomValue(String insertedTextString);

        void selectProduct(Product product);

        void getInitialValue();

        void returnCoins();
    }

}
