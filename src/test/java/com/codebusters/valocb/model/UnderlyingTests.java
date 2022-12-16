package com.codebusters.valocb.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UnderlyingTests {

    @Test
    public void shouldCorrectlyComputePrice() {
        Forex forex = new Forex("USD", "EUR", 1);
        Price price = new Price(122, "EUR", forex);
        Underlying underlying = new Underlying("underlying name", price);
        assertEquals(price.getEurAmount(), underlying.getPrice());
    }

}
