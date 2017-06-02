package com.ariets.kata.model;


import android.support.annotation.NonNull;

import java.util.List;

public class ProductDispenser {

    private final List<Product> productCollection;

    public ProductDispenser(List<Product> productCollection) {
        this.productCollection = productCollection;
    }

    public boolean isAvailable(Product product) {
        return productCollection.contains(product);
    }

    public boolean dispenseItem(@NonNull Product product) {
        return productCollection.remove(product);
    }

}
