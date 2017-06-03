package com.ariets.kata.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatterTest {

    @Test
    public void formatDoubleReturnsProperlyForSingleDigit() {
        assertThat(Formatter.formatDouble(1)).isEqualTo("1.00");
    }

    @Test
    public void formatDoubleReturnsProperlyForDoubleDigit() {
        assertThat(Formatter.formatDouble(1.1)).isEqualTo("1.10");
    }

    @Test
    public void formatDoubleReturnsProperlyForTripleDigit() {
        assertThat(Formatter.formatDouble(1.11)).isEqualTo("1.11");
    }

}