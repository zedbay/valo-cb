package com.codebusters.valocb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductTests {

    private Product product;

    @BeforeEach
    public void init() {
        product = new Product("Product name");
    }

    @Test
    public void shouldCorrectlyComputePriceWithNoUnderlying() {
        assertEquals(0, product.getPrice());
    }

    @Test
    public void shouldCorrectlyComputePriceWithUnderlying() {
        Forex forex = new Forex("EUR", "USD", 1);
        Underlying underlying = new Underlying("underlying name", new Price(12, "EUR", forex));
        product.addUnderlying(underlying);
        assertEquals(12, product.getPrice());
    }

    @Test
    public void shouldCorrectlyComputePriceWithManyUnderlyings() {
        Forex forex = new Forex("EUR", "EUR", 1);
        Underlying underlying4 = new Underlying("underlying 4", new Price(12, "EUR", forex));
        Underlying underlying1 = new Underlying("underlying 1", new Price(122, "EUR", forex));
        Underlying underlying2 = new Underlying("underlying 2", new Price(1, "EUR", forex));
        Underlying underlying3 = new Underlying("underlying 3", new Price(21, "EUR", forex));
        product.addUnderlying(underlying4);
        product.addUnderlying(underlying1);
        product.addUnderlying(underlying2);
        product.addUnderlying(underlying3);
        assertEquals(156, product.getPrice());
    }

    @Test
    public void shouldCorrectlyAddUnderlying() {
        Forex forex = new Forex("EUR", "EUR", 1);
        assertEquals(0, product.getUnderlyings().size());
        Underlying underlying4 = new Underlying("underlying 4", new Price(12, "EUR", forex));
        product.addUnderlying(underlying4);
        assertEquals(1, product.getUnderlyings().size());
        Underlying underlying1 = new Underlying("underlying 1", new Price(122, "EUR", forex));
        product.addUnderlying(underlying1);
        assertEquals(2, product.getUnderlyings().size());
    }


}
