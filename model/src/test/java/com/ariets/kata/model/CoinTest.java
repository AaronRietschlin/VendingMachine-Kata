package com.ariets.kata.model;

import org.junit.Test;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
import static org.assertj.core.api.Assertions.assertThat;


public class CoinTest {

    @Test
    public void pennySanityTest() {
        assertThat(PENNY.getValue()).isEqualTo(0.01);
    }

    @Test
    public void nickelSanityTest() {
        assertThat(NICKEL.getValue()).isEqualTo(0.05);
    }

    @Test
    public void dimeSanityTest() {
        assertThat(DIME.getValue()).isEqualTo(0.10);
    }

    @Test
    public void quarterSanityTest() {
        assertThat(QUARTER.getValue()).isEqualTo(0.25);
    }

}