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
        Integer count = productMap.get(product);
        return count != null && count > 0;
    }

    public boolean dispenseItem(@NonNull Product product) {
        Integer count = productMap.get(product);
        if (count != null && count-- > 0) {
            productMap.put(product, count);
            return true;
        }
        return false;
    }


}
