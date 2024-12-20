package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CreateSellerRequestTest {

    @Test
    void shouldCreateCreateSellerRequest() {
        String code = "SELLER123";

        CreateSellerRequest request = new CreateSellerRequest(code, List.of());

        assertEquals(code, request.code());
    }

    @Test
    void shouldGenerateHashCode() {
        String code = "SELLER123";

        CreateSellerRequest request = new CreateSellerRequest(code, List.of());

        int expectedHashCode = code.hashCode();
        assertEquals(expectedHashCode, request.hashCode());
    }

    @Test
    void shouldEqualSameObjects() {
        String code = "SELLER123";

        CreateSellerRequest request1 = new CreateSellerRequest(code, List.of());
        CreateSellerRequest request2 = new CreateSellerRequest(code, List.of());

        assertEquals(request1, request2);
    }

    @Test
    void shouldNotEqualDifferentObjects() {
        CreateSellerRequest request1 = new CreateSellerRequest("SELLER123", List.of());
        CreateSellerRequest request2 = new CreateSellerRequest("SELLER456", List.of());

        assertNotEquals(request1, request2);
    }
}
