package com.ariets.kata.model;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
import static com.ariets.kata.model.Product.COLA;
import static com.ariets.kata.model.VendingResult.INSUFFICIENT_FUNDS;
import static com.ariets.kata.model.VendingResult.SOLD_OUT;
import static com.ariets.kata.model.VendingResult.SUCCESS;
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

    @Test
    public void selectProductReturnsSuccessWhenFundsAreEqualToCola() {
        doReturn(true).when(mockMoneyValidator).isValid(1.00);
        vendingMachine.insertValue(1.00);

        assertThat(vendingMachine.selectProduct(COLA)).isEqualTo(SUCCESS);
        verify(mockMoneyValidator).isValid(1.00);
    }

    @Test
    public void selectProductReturnsSuccessWhenFundsAreGreaterThanCola() {
        doReturn(true).when(mockMoneyValidator).isValid(10.00);
        vendingMachine.insertValue(10.00);

        assertThat(vendingMachine.selectProduct(COLA)).isEqualTo(SUCCESS);
        verify(mockMoneyValidator).isValid(10.00);
    }

    // TODO - Code is still wrong here.
    @Test
    public void selectProductReturnsInSufficientFundsWhenCurrentValueIsNotEnough() {
        doReturn(true).when(mockMoneyValidator).isValid(0.10);
        vendingMachine.insertValue(0.10);

        assertThat(vendingMachine.selectProduct(COLA)).isEqualTo(INSUFFICIENT_FUNDS);
        verify(mockMoneyValidator).isValid(0.10);
    }

    @Test
    public void selectProductReturnsSoldOutWhenCurrentProductIsSoldOut() {
        doReturn(true).when(mockMoneyValidator).isValid(1.00);
        // TODO - Mock out that the product is sold out!
        vendingMachine.insertValue(1.00);

        assertThat(vendingMachine.selectProduct(COLA)).isEqualTo(SOLD_OUT);
        verify(mockMoneyValidator).isValid(1.00);
    }

    @Test
    public void selectProductSubtractsValueProperly() {
        doReturn(true).when(mockMoneyValidator).isValid(10.00);
        vendingMachine.insertValue(10.00);

        vendingMachine.selectProduct(COLA);

        assertThat(vendingMachine.getCurrentValue()).isEqualTo(9.00);
        verify(mockMoneyValidator).isValid(10.00);
    }

    @Test
    public void getChangeReturnsNullWhenNoFunds() {
        assertThat(vendingMachine.getChange()).isNull();
    }

    @Test @Ignore("TODO - Fix change precision issue.")
    public void getChangeReturnsProper100() {
        doReturn(true).when(mockMoneyValidator).isValid(1.00);
        vendingMachine.insertValue(1.00);

        List<Coin> change = vendingMachine.getChange();

        assertThat(change).hasSize(4).contains(QUARTER, QUARTER, QUARTER, QUARTER);
        verify(mockMoneyValidator).isValid(1.00);
    }

    @Test @Ignore("TODO - Fix change precision issue.")
    public void getChangeReturnsProperFor90() {
        doReturn(true).when(mockMoneyValidator).isValid(0.92);
        vendingMachine.insertValue(0.92);

        List<Coin> change = vendingMachine.getChange();

        assertThat(change).hasSize(8)
                .contains(QUARTER, QUARTER, QUARTER, DIME, NICKEL, PENNY, PENNY);
        verify(mockMoneyValidator).isValid(0.92);
    }

    @Test @Ignore("TODO - Fix change precision issue.")
    public void getChangeReturnsProperFor22() {
        doReturn(true).when(mockMoneyValidator).isValid(0.22);
        vendingMachine.insertValue(0.22);

        List<Coin> change = vendingMachine.getChange();

        assertThat(change).hasSize(4).contains(DIME, DIME, PENNY, PENNY);
        verify(mockMoneyValidator).isValid(0.22);
    }

    @Test @Ignore("TODO - Fix change precision issue.")
    public void getChangeReturnsProperFor9() {
        doReturn(true).when(mockMoneyValidator).isValid(0.09);
        vendingMachine.insertValue(0.09);

        List<Coin> change = vendingMachine.getChange();

        assertThat(change).hasSize(5).contains(NICKEL, PENNY, PENNY, PENNY, PENNY);
        verify(mockMoneyValidator).isValid(0.09);
    }

}