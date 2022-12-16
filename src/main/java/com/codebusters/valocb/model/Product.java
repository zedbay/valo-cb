package com.codebusters.valocb.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Product {

    private final String name;

    private final Set<Underlying> underlyings = new HashSet<>();

    public Product(String name) {
        this.name = name;
    }

    public void addUnderlying(Underlying underlying) {
        this.underlyings.add(underlying);
    }

    public float getPrice() {
        return underlyings.stream()
                .map(Underlying::getPrice)
                .reduce(0f, Float::sum);
    }

    public String toString() {
        return "Product " + name + ": \n" + underlyings.stream().map(Underlying::toString).reduce(String::concat);
    }

    public String getName() {
        return name;
    }

    public Set<Underlying> getUnderlyings() {
        return underlyings;
    }
}
