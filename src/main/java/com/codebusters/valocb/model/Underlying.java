package com.codebusters.valocb.model;

import com.codebusters.valocb.model.Price;
import jakarta.persistence.*;

public class Underlying {

    private Price price;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public void setPrice(Price price) {
        this.price = price;
    }

}
