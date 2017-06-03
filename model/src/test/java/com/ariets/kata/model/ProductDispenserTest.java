package com.ariets.kata.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.COLA;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductDispenserTest {

    @Test
    public void isAvailableReturnsTrueWhenProductIsAvailable() {
        Map<Product, Integer> map = demandMap(CANDY, 1);
        ProductDispenser productDispenser = new ProductDispenser(map);

        assertThat(productDispenser.isAvailable(CANDY)).isTrue();
    }

    @Test
    public void isAvailableReturnsFalseWhenProductIsNotAvailable() {
        Map<Product, Integer> map = demandMap(CANDY, 0);
        ProductDispenser productDispenser = new ProductDispenser(map);

        assertThat(productDispenser.isAvailable(CANDY)).isFalse();
    }

    @Test
    public void isAvailableReturnsFalseWhenGivenProductIsNotInMap() {
        Map<Product, Integer> map = demandMap(COLA, 0);
        ProductDispenser productDispenser = new ProductDispenser(map);

        assertThat(productDispenser.isAvailable(CANDY)).isFalse();
    }

    @Test
    public void isAvailableReturnsFalseWhenValueIsNull() {
        Map<Product, Integer> map = demandMap(CANDY, null);
        ProductDispenser productDispenser = new ProductDispenser(map);

        assertThat(productDispenser.isAvailable(CANDY)).isFalse();
    }

    @Test
    public void dispenseItemDecrementsCountWhenRemoved() {
        Map<Product, Integer> map = demandMap(CANDY, 1);
        ProductDispenser productDispenser = new ProductDispenser(map);

        productDispenser.dispenseItem(CANDY);

        assertThat(map.get(CANDY)).isZero();
    }

    @Test
    public void dispenseItemReturnsTrueWhenItemIsRemoved() {
        Map<Product, Integer> map = demandMap(CANDY, 1);
        ProductDispenser productDispenser = new ProductDispenser(map);

        assertThat(productDispenser.dispenseItem(CANDY)).isTrue();
    }

    @Test
    public void dispenseItemReturnsFalseWhenItemIsNotPresent() {
        Map<Product, Integer> map = demandMap(COLA, 1);
        ProductDispenser productDispenser = new ProductDispenser(map);

        assertThat(productDispenser.dispenseItem(CANDY)).isFalse();
    }
    
    private Map<Product, Integer> demandMap(Product product, Integer count) {
        Map<Product, Integer> map = new HashMap<>();
        map.put(product, count);
        return map;
    }
}