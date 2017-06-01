package com.ariets.kata.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.Product;

public class AndroidDisplayProvider implements DisplayProvider {

    @NonNull
    private final Context context;

    public AndroidDisplayProvider(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public String displayExactChange() {
        return null;
    }

    @Override
    public String displayInsertCoin() {
        return null;
    }

    @Override
    public String displayPrice(Product product) {
        return null;
    }

    @Override
    public String displaySoldOut() {
        return null;
    }

    @Override
    public String displayThankYou() {
        return null;
    }
}
