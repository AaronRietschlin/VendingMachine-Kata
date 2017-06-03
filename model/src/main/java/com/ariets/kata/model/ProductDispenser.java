package com.ariets.kata.model;


import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

public class ProductDispenser {

    private final Map<Product, Integer> productMap;

    public ProductDispenser(Map<Product, Integer> productCollection) {
        this.productMap = productCollection;
    }

    public boolean isAvailable(Product product) {
        return false;
    }

    public boolean dispenseItem(@NonNull Product product) {
        return false;
    }


}
