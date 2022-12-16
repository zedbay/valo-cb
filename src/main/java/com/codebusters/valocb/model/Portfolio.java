package com.codebusters.valocb.model;

import java.util.HashSet;
import java.util.Set;

public class Portfolio {
    private final String name;

    private final Set<Product> products = new HashSet<>();

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

    public String getName() {
        return name;
    }

    public Set<Product> getProducts() {
        return products;
    }

}
