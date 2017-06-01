package com.ariets.kata.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ariets.kata.R;
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
        return context.getString(R.string.exact_change);
    }

    @Override
    public String displayInsertCoin() {
        return context.getString(R.string.insert_coin);
    }

    @Override
    public String displayPrice(Product product) {
        return context.getString(R.string.price, product.getDisplayPrice());
    }

    @Override
    public String displaySoldOut() {
        return context.getString(R.string.sold_out);
    }

    @Override
    public String displayThankYou() {
        return context.getString(R.string.thank_you);
    }
}
