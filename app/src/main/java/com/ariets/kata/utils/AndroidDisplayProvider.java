package com.ariets.kata.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ariets.kata.R;
import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.Formatter;
import com.ariets.kata.model.Product;

/**
 * A {@link DisplayProvider} that interacts with the android framework.
 *
 * This was done in this manner to silo the Android code to force as much of the logic to be unit
 * tested in the local JVM as possible. This has the added benefit of being able to display in
 * another language in a manner supported by the android system.
 */
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

    @Override
    public String displayPrice(double value) {
        return context.getString(R.string.price, Formatter.formatDouble(value));
    }
}
