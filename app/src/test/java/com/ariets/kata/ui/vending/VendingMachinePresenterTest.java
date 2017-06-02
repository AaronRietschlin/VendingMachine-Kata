package com.ariets.kata.ui.vending;


import com.ariets.kata.model.VendingMachine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class VendingMachinePresenterTest {

    @Mock
    VendingMachine mockVendingMachine;
    @Mock
    VendingMachineContract.View mockView;

    private VendingMachinePresenter presenter;

    @Before
    public void setUp() {
        initMocks(this);
        presenter = new VendingMachinePresenter(mockVendingMachine);
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

}