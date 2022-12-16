package com.codebusters.valocb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PriceTests {

    @Test
    void shouldCorrectlyComputeEurAmount() {
        Forex forex = new Forex("EUR", "EUR", 1);
        Price price = new Price(12, "EUR", forex);
        assertEquals(12, price.getEurAmount());
    }

    @Test
    void shouldCorrectlyComputeNoEurAmount() {
        Forex forex = new Forex("USD", "EUR", 2);
        Price price = new Price(12, "USD", forex);
        assertEquals(24,  price.getEurAmount());
    }

}
