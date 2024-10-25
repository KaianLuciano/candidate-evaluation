package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CreateSellerResponseTest {

    @Test
    void shouldCreateCreateSellerResponse() {
        String code = "SELLER123";

        CreateSellerResponse response = new CreateSellerResponse(code);

        assertEquals(code, response.code());
    }

    @Test
    void shouldGenerateHashCode() {
        String code = "SELLER123";

        CreateSellerResponse response = new CreateSellerResponse(code);

        int expectedHashCode = code.hashCode();
        assertEquals(expectedHashCode, response.hashCode());
    }

    @Test
    void shouldEqualSameObjects() {
        String code = "SELLER123";

        CreateSellerResponse response1 = new CreateSellerResponse(code);
        CreateSellerResponse response2 = new CreateSellerResponse(code);

        assertEquals(response1, response2);
    }

    @Test
    void shouldNotEqualDifferentObjects() {
        CreateSellerResponse response1 = new CreateSellerResponse("SELLER123");
        CreateSellerResponse response2 = new CreateSellerResponse("SELLER456");

        assertNotEquals(response1, response2);
    }
}
