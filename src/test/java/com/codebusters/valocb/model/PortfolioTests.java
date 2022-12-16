package com.codebusters.valocb.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PortfolioTests {

    @Test
    void shouldCorrectlyComputeCapitalWithNoProduct() {
        Portfolio portfolio = new Portfolio("Portfolio name");
        assertEquals(0, portfolio.getPrice());
    }

    @Test
    void shouldAddProduct() {
        Product product = new Product("Product name");
        Portfolio portfolio = new Portfolio("Portfolio name");
        portfolio.addProduct(product);

        assertEquals(1, portfolio.getProducts().size());
    }

    @Test
    void shouldCorrectlyComputePriceWithOneProduct() {
        Product product = new Product("Product name");
        Forex forex = new Forex("EUR", "EUR", 1);
        Underlying underlying = new Underlying("underlying name", new Price(12, "EUR", forex));
        product.addUnderlying(underlying);

        Portfolio portfolio = new Portfolio("Portfolio name");
        portfolio.addProduct(product);

        assertEquals(12, portfolio.getPrice());
    }

    @Test
    void shouldCorrectlyComputePriceWithManyProduct() {
        Product product = new Product("Product name");
        Product productBis = new Product("Product name bis");
        Forex forex = new Forex("EUR", "EUR", 1);
        Underlying underlying = new Underlying("underlying name", new Price(12, "EUR", forex));
        product.addUnderlying(underlying);
        productBis.addUnderlying(underlying);
        Portfolio portfolio = new Portfolio("Portfolio name");
        portfolio.addProduct(product);
        portfolio.addProduct(productBis);

        assertEquals(24, portfolio.getPrice());
    }

}
