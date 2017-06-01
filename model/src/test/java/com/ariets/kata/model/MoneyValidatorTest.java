package com.ariets.kata.model;

import org.junit.Before;
import org.junit.Test;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.CHIPS;
import static com.ariets.kata.model.Product.COLA;
import static org.assertj.core.api.Assertions.assertThat;

public class MoneyValidatorTest {

    private static final double PRICE_LARGE = 1.70;
    private static final double PRICE_SMALL = 0.10;

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

    @Test
    public void isValidValueReturnsFalseForPenny() {
        assertThat(moneyValidator.isValid(PENNY.getValue())).isFalse();
    }

    @Test
    public void isValidValueReturnsTrueForNickel() {
        assertThat(moneyValidator.isValid(NICKEL.getValue())).isTrue();
    }

    @Test
    public void isValidValueReturnsFalseForCustom() {
        assertThat(moneyValidator.isValid(1.5)).isFalse();
    }

    @Test
    public void isCorrectPaymentForChipsReturnsTrueForExactAmount() {
        assertThat(moneyValidator.isCorrectPayment(CHIPS.getPrice(), CHIPS)).isTrue();
    }

    @Test
    public void isCorrectPaymentForChipsReturnsTrueForGreaterAmount() {
        assertThat(moneyValidator.isCorrectPayment(PRICE_LARGE, CHIPS)).isTrue();
    }

    @Test
    public void isCorrectPaymentForChipsReturnsFalseForSmallerAmount() {
        assertThat(moneyValidator.isCorrectPayment(PRICE_SMALL, CHIPS)).isFalse();
    }

    @Test
    public void isCorrectPaymentForColaReturnsTrueForExactAmount() {
        assertThat(moneyValidator.isCorrectPayment(COLA.getPrice(), COLA)).isTrue();
    }

    @Test
    public void isCorrectPaymentForColaReturnsTrueForGreaterAmount() {
        assertThat(moneyValidator.isCorrectPayment(PRICE_LARGE, COLA)).isTrue();
    }

    @Test
    public void isCorrectPaymentForColaReturnsFalseForSmallerAmount() {
        assertThat(moneyValidator.isCorrectPayment(PRICE_SMALL, COLA)).isFalse();
    }

    @Test
    public void isCorrectPaymentForCandyReturnsTrueForExactAmount() {
        assertThat(moneyValidator.isCorrectPayment(CANDY.getPrice(), CANDY)).isTrue();
    }

    @Test
    public void isCorrectPaymentForCandyReturnsTrueForGreaterAmount() {
        assertThat(moneyValidator.isCorrectPayment(PRICE_LARGE, CANDY)).isTrue();
    }

    @Test
    public void isCorrectPaymentForCandyReturnsFalseForSmallerAmount() {
        assertThat(moneyValidator.isCorrectPayment(PRICE_SMALL, CANDY)).isFalse();
    }

}