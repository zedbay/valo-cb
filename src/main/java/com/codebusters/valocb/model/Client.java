package com.codebusters.valocb.model;


import java.util.HashMap;
import java.util.Map;

public class Client {

    private String name;

    private Map<Product, Integer> ownedProducts = new HashMap<>();

    public Client(String name) {
        this.name = name;
    }

    public void addOwnedProduct(Product product, int quantity) {
        this.ownedProducts.put(product, quantity);
    }

    public float getCapital() {
        return ownedProducts
                .entrySet()
                .stream()
                .map(e -> e.getKey().getPrice() * e.getValue())
                .reduce(0f, Float::sum);
    }

    public String toString() {
        return "Client (" + name + ")\n" + ownedProducts.size();
    }

    public String getName() {
        return name;
    }

    public Map<Product, Integer> getOwnedProducts() {
        return ownedProducts;
    }
}
