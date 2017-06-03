package com.ariets.kata.ui.vending;


import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.VendingMachine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.ariets.kata.model.Coin.NICKEL;
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
        presenter.attachView(mockView);

        presenter.insertCoin(NICKEL);

        verify(mockView).setCurrentValue("$1.50");
        verify(mockVendingMachine).getFormattedCurrentValue();
    }

    @Test
    public void getInitiaLDisplayForwardsToDisplayProvider() {
        presenter.getInitialDisplay();

        verify(mockDisplayProvider).displayInsertCoin();
    }

}