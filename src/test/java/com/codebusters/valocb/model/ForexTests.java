package com.codebusters.valocb.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class ForexTests {

    @Test
    void shouldCorrectlyGetKey() {
        Forex forex = new Forex("EUR", "USD", 2);
        Forex forexBis = new Forex("USD", "EUR", 0.5f);
        assertEquals("USD", forexBis.getKey());
        assertEquals("USD", forex.getKey());
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Forex forex = new Forex("EUR","USD", 0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, forex::getRateToEur);
        assertEquals("Rate is equal to zero", exception.getMessage());
    }

    @Test
    void shouldCorrectlyComputeRateToEur() {
        Forex forex = new Forex("EUR", "USD", 2);
        Forex forexBis = new Forex("USD", "EUR", 0.5f);
        assertEquals(0.5f, forexBis.getRateToEur());
        assertEquals(0.5f, forex.getRateToEur());
    }
}
