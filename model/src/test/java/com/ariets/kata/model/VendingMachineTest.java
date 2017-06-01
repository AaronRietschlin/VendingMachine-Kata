package com.ariets.kata.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class VendingMachineTest {

    @Mock
    MoneyValidator mockMoneyValidator;
    @Mock
    DisplayProvider mockDisplayProvider;

    private VendingMachine vendingMachine;

    @Before
    public void setUp() {
        initMocks(this);
        vendingMachine = new VendingMachine(mockMoneyValidator, mockDisplayProvider);
    }

    @Test
    public void insertCoinWithInvalidCoinReturnsFalse() {
        assertThat(vendingMachine.insertCoin(PENNY)).isFalse();
    }

    @Test
    public void currentValueStartsAtZero() {
        double currentValue = vendingMachine.getCurrentValue();
        assertThat(currentValue).isEqualTo(0d);
    }

    @Test
    public void insertCoinIncreasesCurrentValueProperly() {
        doReturn(true).when(mockMoneyValidator).isValid(NICKEL);

        vendingMachine.insertCoin(NICKEL);

        assertThat(vendingMachine.getCurrentValue()).isEqualTo(NICKEL.getValue());
        verify(mockMoneyValidator).isValid(NICKEL);
    }

    @Test
    public void insertCoinIncreasesProperlyWithMultipleValues() {
        doReturn(true).when(mockMoneyValidator).isValid(any(Coin.class));
        vendingMachine.insertCoin(NICKEL);
        vendingMachine.insertCoin(QUARTER);
        vendingMachine.insertCoin(DIME);

        assertThat(vendingMachine.getCurrentValue()).isEqualTo(0.40);
        verify(mockMoneyValidator, times(3)).isValid(any(Coin.class));
    }

}