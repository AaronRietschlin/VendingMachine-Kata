package com.ariets.kata.model;

/**
 * An interface that will provide valid strings to display to the user.
 */
public interface DisplayProvider {

    /**
     * @return "exact change"
     */
    String displayExactChange();

    /**
     * @return "Insert Coin"
     */
    String displayInsertCoin();

    /**
     * Displays the price of the given product in a human readable form.
     *
     * @param product The product in which we need the price of.
     */
    String displayPrice(Product product);

    /**
     * @return "Sold out"
     */
    String displaySoldOut();

    /**
     * @return "Thank you"
     */
    String displayThankYou();

    /**
     * Displays the given value in a readable format.
     */
    String displayPrice(double value);

}
