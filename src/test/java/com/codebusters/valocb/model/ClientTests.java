package com.codebusters.valocb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientTests {

    private Client client;


    @BeforeEach
    public void init() {
        client = new Client("Client name");
    }

    @Test
    public void shouldCorrectlyComputeCapitalWithNoProduct() {
        assertEquals(0, client.getCapital());
    }

    @Nested
    public class ClientTestsWithProduct {

        // Why commented ???
//        @BeforeEach
//        public void init() {
//            Product product = new Product("Product name");
//            Underlying underlying = new Underlying("underlying name", new Price(12, "EUR"));
//            product.addUnderlying(underlying);
//            client.addOwnedProduct(product, 1);
//        }
//
//        @Test
//        public void shouldAddProduct() {
//            assertEquals(1, client.getOwnedProducts().size());
//        }
//
//        @Test
//        public void shouldCorrectlyComputePriceWithOneProduct() {
//            assertEquals(12, client.getCapital());
//        }
//
//        @Test
//        public void shouldCorrectlyComptePriceWithProductPresentInQuantity() {
//            Product product = new Product("Product name");
//            Underlying underlying = new Underlying("underlying name 1", new Price(120, "EUR"));
//            product.addUnderlying(underlying);
//            client.addOwnedProduct(product, 2);
//            assertEquals(252, client.getCapital());
//        }

    }


}
