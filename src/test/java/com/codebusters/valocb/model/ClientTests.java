package com.codebusters.valocb.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientTests {

    @Test
    void shouldCorrectlyComputeCapitalWithNoProduct() {
        Client client = new Client("Client name");
        assertEquals(0, client.getCapital());
    }

    @Test
    void shouldAddProduct() {
        Product product = new Product("Product name");
        Client client = new Client("Client name");
        client.addOwnedProduct(product, 1);

        assertEquals(1, client.getOwnedProducts().size());
    }

    @Test
    void shouldCorrectlyComputeCapitalWithOneProduct() {
        Product product = new Product("Product name");
        Forex forex = new Forex("EUR", "EUR", 1);
        Underlying underlying = new Underlying("underlying name", new Price(12, "EUR", forex));
        product.addUnderlying(underlying);
        Client client = new Client("Client name");
        client.addOwnedProduct(product, 2);

        assertEquals(24, client.getCapital());
    }

    @Test
    void shouldCorrectlyComputeCapitalWithManyProduct() {
        Product product = new Product("Product name");
        Product productBis = new Product("Product name bis");
        Forex forex = new Forex("EUR", "EUR", 1);
        Underlying underlying = new Underlying("underlying name", new Price(12, "EUR", forex));
        product.addUnderlying(underlying);
        productBis.addUnderlying(underlying);
        Client client = new Client("Client name");
        client.addOwnedProduct(product, 2);
        client.addOwnedProduct(productBis, 3);

        assertEquals(60, client.getCapital());
    }




}
