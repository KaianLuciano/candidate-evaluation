package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UpdateSellerRequestTest {

    @Test
    void shouldGenerateEquals() {
        String code = "SELLER123";
        List<Payment> payments = Collections.emptyList();

        UpdateSellerRequest request1 = new UpdateSellerRequest(code, payments);
        UpdateSellerRequest request2 = new UpdateSellerRequest(code, payments);

        assertEquals(request1, request2);
    }

    @Test
    void shouldNotBeEqualWithDifferentCode() {
        UpdateSellerRequest request1 = new UpdateSellerRequest("SELLER123", Collections.emptyList());
        UpdateSellerRequest request2 = new UpdateSellerRequest("SELLER456", Collections.emptyList());

        assertNotEquals(request1, request2);
    }

    @Test
    void shouldNotBeEqualWithDifferentPayments() {
        UpdateSellerRequest request1 = new UpdateSellerRequest("SELLER123", Collections.emptyList());
        UpdateSellerRequest request2 = new UpdateSellerRequest("SELLER123",
                List.of(new Payment("123456", BigDecimal.valueOf(100.0), null)));

        assertNotEquals(request1, request2);
    }

    @Test
    void shouldGetCode() {
        String code = "SELLER123";
        UpdateSellerRequest updateSellerRequest = new UpdateSellerRequest(code, Collections.emptyList());

        assertEquals(code, updateSellerRequest.code());
    }

    @Test
    void shouldGetPayments() {
        List<Payment> payments = List.of(new Payment("123456", BigDecimal.valueOf(100.0), null));
        UpdateSellerRequest updateSellerRequest = new UpdateSellerRequest("SELLER123", payments);

        assertEquals(payments, updateSellerRequest.payments());
    }
}
