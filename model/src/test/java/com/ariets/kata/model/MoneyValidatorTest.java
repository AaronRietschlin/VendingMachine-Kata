package com.ariets.kata.model;

import org.junit.Before;
import org.junit.Test;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
import static org.assertj.core.api.Assertions.assertThat;

public class MoneyValidatorTest {

    private MoneyValidator moneyValidator;

    @Before
    public void setUp() {
        moneyValidator = new MoneyValidator();
    }

    @Test
    public void isValidCoinReturnsTrueForQuarter() {
        assertThat(moneyValidator.isValid(QUARTER)).isTrue();
    }

    @Test
    public void isValidCoinReturnsTrueForNickel() {
        assertThat(moneyValidator.isValid(NICKEL)).isTrue();
    }

    @Test
    public void isValidCoinReturnsTrueForDime() {
        assertThat(moneyValidator.isValid(DIME)).isTrue();
    }

    @Test
    public void isValidCoinReturnsFalseForPenny() {
        assertThat(moneyValidator.isValid(PENNY)).isFalse();
    }


}