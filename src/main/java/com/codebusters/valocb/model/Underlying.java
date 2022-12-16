package com.codebusters.valocb.model;

public class Underlying {

    private final Price price;

    private final String name;


    public Underlying(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public float getPrice() {
        return this.price.getEurAmount();
    }

    public String toString() {
        return "Underlying:" + name + " -> " + getPrice() + " EUR. \n ";
    }

}
