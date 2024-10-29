package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CreateSellerResponseTest {

    @Test
    void shouldCreateCreateSellerResponse() {
        String code = "SELLER123";

        CreateSellerResponse response = new CreateSellerResponse(code, List.of());

        assertEquals(code, response.code());
    }

    @Test
    void shouldGenerateHashCode() {
        String code = "SELLER123";

        CreateSellerResponse response = new CreateSellerResponse(code, List.of());

        int expectedHashCode = code.hashCode();
        assertEquals(expectedHashCode, response.hashCode());
    }

    @Test
    void shouldEqualSameObjects() {
        String code = "SELLER123";

        CreateSellerResponse response1 = new CreateSellerResponse(code, List.of());
        CreateSellerResponse response2 = new CreateSellerResponse(code, List.of());

        assertEquals(response1, response2);
    }

    @Test
    void shouldNotEqualDifferentObjects() {
        CreateSellerResponse response1 = new CreateSellerResponse("SELLER123", List.of());
        CreateSellerResponse response2 = new CreateSellerResponse("SELLER456", List.of());

        assertNotEquals(response1, response2);
    }
}
