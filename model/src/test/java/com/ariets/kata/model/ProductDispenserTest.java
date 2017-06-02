package com.ariets.kata.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.COLA;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductDispenserTest {

    @Test
    public void dispenseItemRemovesProduct() {
        List<Product> list = demandProductList(CANDY);
        ProductDispenser productDispenser = new ProductDispenser(list);

        assertThat(productDispenser.dispenseItem(CANDY)).isTrue();
        assertThat(list).hasSize(0);
    }

    @Test
    public void dispenseItemDoesNothingWhenProductIsNotPresent() {
        ProductDispenser productDispenser = new ProductDispenser(demandProductList(CANDY));

        assertThat(productDispenser.dispenseItem(COLA)).isFalse();
    }

    @Test
    public void isAvailableReturnsTrueForProductThatIsInList() {
        ProductDispenser productDispenser = new ProductDispenser(demandProductList(CANDY));

        assertThat(productDispenser.isAvailable(CANDY)).isTrue();
    }

    @Test
    public void isAvailableReturnsFalseForProductThatIsNotInList() {
        ProductDispenser productDispenser = new ProductDispenser(demandProductList(CANDY));

        assertThat(productDispenser.isAvailable(COLA)).isFalse();
    }

    private List<Product> demandProductList(Product product) {
        List<Product> list = new ArrayList<>();
        list.add(product);
        return list;
    }
}