package com.ariets.kata.ui.vending;


import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.VendingMachine;
import com.ariets.kata.model.VendingResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Product.COLA;
import static com.ariets.kata.ui.vending.VendingError.INVALID_COIN;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class VendingMachinePresenterTest {

    @Mock
    VendingMachine mockVendingMachine;
    @Mock
    VendingMachineContract.View mockView;
    @Mock
    DisplayProvider mockDisplayProvider;

    private VendingMachinePresenter presenter;

    @Before
    public void setUp() {
        initMocks(this);
        presenter = new VendingMachinePresenter(mockVendingMachine, mockDisplayProvider);
    }

    @Test
    public void attachViewSetsView() {
        presenter.attachView(mockView);

        assertEquals(mockView, presenter.view);
    }

    @Test
    public void detachViewNullsOutView() {
        presenter.attachView(mockView);

        presenter.detachView();

        assertNull(presenter.view);
    }

    @Test
    public void insertCoinSetsCurrentValue() {
        doReturn("$1.50").when(mockVendingMachine).getFormattedCurrentValue();
        doReturn(true).when(mockVendingMachine).insertCoin(NICKEL);
        presenter.attachView(mockView);

        presenter.insertCoin(NICKEL);

        verify(mockView).setCurrentValue("$1.50");
        verify(mockVendingMachine).insertCoin(NICKEL);
        verify(mockVendingMachine).getFormattedCurrentValue();
    }

    @Test
    public void initializeCalsViewProperly() {
        String text = "text";
        doReturn(text).when(mockDisplayProvider).displayInsertCoin();
        presenter.attachView(mockView);

        presenter.initialize();

        verify(mockView).setCurrentDisplay(text);
    }

    @Test
    public void insertCustomValueWithInvalidTextReturnsInvalidCoin() {
        presenter.attachView(mockView);

        presenter.insertCustomValue("asdf");

        verify(mockView).onError(INVALID_COIN);
    }

    @Test
    public void insertCustomValueWithInvalidNumberReturnsInvalidCoin() {
        presenter.attachView(mockView);

        presenter.insertCustomValue("11.11.1");

        verify(mockView).onError(INVALID_COIN);
    }

    @Test
    public void insertCustomValueWithValidValueSetsValueOnView() {
        String value = "0.25";
        presenter.attachView(mockView);
        doReturn(true).when(mockVendingMachine).insertValue(Double.valueOf(value));
        doReturn(value).when(mockVendingMachine).getFormattedCurrentValue();

        presenter.insertCustomValue(value);

        verify(mockView).setCurrentValue(value);
        verify(mockVendingMachine).insertValue(Double.valueOf(value));
    }

    @Test
    public void insertCustomValueWithPennyValueCallsInvalidCoin() {
        String value = "0.01";
        presenter.attachView(mockView);
        doReturn(value).when(mockVendingMachine).getFormattedCurrentValue();

        presenter.insertCustomValue(value);

        verify(mockView).onError(INVALID_COIN);
    }

    @Test
    public void selectProductWithInsufficientFundsCallsViewProperly() {
        doReturn(VendingResult.INSUFFICIENT_FUNDS).when(mockVendingMachine).selectProduct(COLA);
        doReturn("1.50").when(mockDisplayProvider).displayPrice(COLA);
        presenter.attachView(mockView);

        presenter.selectProduct(COLA);

        verify(mockView).setCurrentDisplay("1.50");
        verify(mockVendingMachine).selectProduct(COLA);
    }

}