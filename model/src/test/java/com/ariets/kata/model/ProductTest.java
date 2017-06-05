package com.ariets.kata.model;

import org.junit.Test;

import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.CHIPS;
import static com.ariets.kata.model.Product.COLA;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void candySanityTest() {
        assertThat(CANDY.getPrice()).isEqualTo(0.65);
    }

    @Test
    public void colaSanityTest() {
        assertThat(COLA.getPrice()).isEqualTo(1.00);
    }

    @Test
    public void chipsSanityTest() {
        assertThat(CHIPS.getPrice()).isEqualTo(0.50);
    }

    @Test
    public void candyDisplayPriceSanityTest() {
        assertThat(CANDY.getDisplayPrice()).isEqualTo("0.65");
    }

    @Test
    public void colaDisplayPriceSanityTest() {
        assertThat(COLA.getDisplayPrice()).isEqualTo("1.00");
    }

    @Test
    public void chipsDisplayPriceSanityTest() {
        assertThat(CHIPS.getDisplayPrice()).isEqualTo("0.50");
    }

}