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
        return context.getString(R.string.display_exact_change);
    }

    @Override
    public String displayInsertCoin() {
        return context.getString(R.string.display_insert_coin);
    }

    @Override
    public String displayPrice(Product product) {
        return context.getString(R.string.display_price, product.getDisplayPrice());
    }

    @Override
    public String displaySoldOut() {
        return context.getString(R.string.display_sold_out);
    }

    @Override
    public String displayThankYou() {
        return context.getString(R.string.display_thank_you);
    }
}
