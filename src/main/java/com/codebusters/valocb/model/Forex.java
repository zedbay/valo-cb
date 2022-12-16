package com.codebusters.valocb.model;

public class Forex {

    static final String eurosCurrency = "EUR";

    private final String currencyFrom;

    private final String currencyTo;

    private final float rate;

    public Forex(String currencyFrom, String currencyTo, float rate) {
        this.rate = rate;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    public String getKey() {
        return Forex.eurosCurrency.equals(currencyFrom) ? currencyTo : currencyFrom;
    }

    public float getRateToEur() {
        if (Forex.eurosCurrency.equals(currencyTo)) {
            return rate;
        }

        if (rate == 0) {
            throw new IllegalArgumentException("Rate is equal to zero");
        }

        return 1 / rate;
    }

}
