package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CreateSellerRequestTest {

    @Test
    void shouldCreateCreateSellerRequest() {
        String code = "SELLER123";

        CreateSellerRequest request = new CreateSellerRequest(code);

        assertEquals(code, request.code());
    }

    @Test
    void shouldGenerateHashCode() {
        String code = "SELLER123";

        CreateSellerRequest request = new CreateSellerRequest(code);

        int expectedHashCode = code.hashCode();
        assertEquals(expectedHashCode, request.hashCode());
    }

    @Test
    void shouldEqualSameObjects() {
        String code = "SELLER123";

        CreateSellerRequest request1 = new CreateSellerRequest(code);
        CreateSellerRequest request2 = new CreateSellerRequest(code);

        assertEquals(request1, request2);
    }

    @Test
    void shouldNotEqualDifferentObjects() {
        CreateSellerRequest request1 = new CreateSellerRequest("SELLER123");
        CreateSellerRequest request2 = new CreateSellerRequest("SELLER456");

        assertNotEquals(request1, request2);
    }
}
