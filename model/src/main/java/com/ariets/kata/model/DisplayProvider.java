package com.ariets.kata.model;

public interface DisplayProvider {

    String displayExactChange();

    String displayInsertCoin();

    String displayPrice(Product product);

    String displaySoldOut();

    String displayThankYou();

}
