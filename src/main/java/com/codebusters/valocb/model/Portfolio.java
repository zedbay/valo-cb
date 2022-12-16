package com.codebusters.valocb.model;


import java.util.HashSet;

public class Portfolio {
    private String name; // should  be final

    private HashSet<Product> products = new HashSet<>(); // should  be final, Use higher level interface

    public Portfolio(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public float getPrice() { // No usage ?
        return this.products.stream()
                .map(Product::getPrice)
                .reduce(0f, Float::sum);
    }
}
