package com.codebusters.valocb.model;

public class Forex {

    private final String currencyFrom;

    private final String currencyTo;

    private final float rate;

    public Forex(String currencyFrom, String currencyTo, float rate) {
        this.rate = rate;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    // "EUR" should be a constant
    public String getKey() {
        return "EUR".equals(currencyFrom) ? currencyTo : currencyFrom;
    }

    public float getRateToEur() {
        if ("EUR".equals(currencyTo)) {
            return rate;
        }

        if (rate == 0) {
            throw new IllegalArgumentException();
        }

        return 1 / rate;

    }
//Care about useless space

}
