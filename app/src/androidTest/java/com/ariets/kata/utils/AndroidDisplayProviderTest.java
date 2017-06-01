package com.ariets.kata.utils;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ariets.kata.MainActivity;
import com.ariets.kata.model.DisplayProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.ariets.kata.model.Product.COLA;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AndroidDisplayProviderTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private DisplayProvider displayProvider;

    @Before
    public void setUp() {
        displayProvider = new AndroidDisplayProvider(activityTestRule.getActivity()
                .getApplicationContext());
    }

    @Test
    public void displayExactChangeReturnsProperly() {
        assertEquals("Exact Change Only".toUpperCase(), displayProvider.displayExactChange());
    }

    @Test
    public void displayInsertCoinReturnsProperly() {
        assertEquals("Insert Coin".toUpperCase(), displayProvider.displayInsertCoin());
    }

    @Test
    public void displayPriceReturnsProperly() {
        assertEquals("Exact Change Only".toUpperCase(), displayProvider.displayExactChange());
    }

    @Test
    public void displaySoldOutReturnsProperly() {
        assertEquals("Sold Out".toUpperCase(), displayProvider.displaySoldOut());
    }

    @Test
    public void displayThankYouReturnsProperly() {
        assertEquals("Thank You".toUpperCase(), displayProvider.displayThankYou());
    }

    @Test
    public void displayPriceReturnsProperlyForCola() {
        assertEquals("PRICE: $1.00".toUpperCase(), displayProvider.displayPrice(COLA));
    }

}