package com.codebusters.valocb.model;


import java.util.HashSet;

public class Portfolio {
    private String name;

    private HashSet<Product> products = new HashSet<>();

    public Portfolio(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public float getPrice() {
        return this.products.stream()
                .map(Product::getPrice)
                .reduce(0f, Float::sum);
    }
}
