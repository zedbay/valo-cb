package com.codebusters.valocb.model;

public class Price {

    private final int amount;

    private final String currency;

    private final Forex forex;

    public Price(int amount, String currency, Forex forex) {
        this.amount = amount;
        this.currency = currency;
        this.forex = forex;
    }

    // "EUR" should be a constant
    public float getEurAmount() {
        if ("EUR".equals(currency)) {
            return amount;
        }
        return amount * forex.getRateToEur();
    }

}
