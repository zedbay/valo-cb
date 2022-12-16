package com.codebusters.valocb.model;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Client {

    private final String name;

    private final Map<Product, Float> ownedProducts = new HashMap<>();

    public Client(String name) {
        this.name = name;
    }

    public void addOwnedProduct(Product product, float quantity) {
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

    public Set<Product> getOwnedProducts() {
        return this.ownedProducts.keySet();
    }

}
