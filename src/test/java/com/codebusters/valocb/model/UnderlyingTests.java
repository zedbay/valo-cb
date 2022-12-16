package com.codebusters.valocb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UnderlyingTests {

    private Underlying underlying;

    @BeforeEach
    public void init() {
        Forex forex = new Forex("USD", "EUR", 1);
        Price price = new Price(122, "EUR", forex);
        this.underlying = new Underlying("underlying name", price);
    }

    @Test
    public void shouldCorrectlyComputePrice() {
        assertEquals(122, underlying.getPrice());
    }

}
