package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.infrastructure.payment.payload.CreatePaymentRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UpdateSellerStatusRequestTest {

    @Test
    void shouldGenerateEquals() {
        String code = "SELLER123";
        List<CreatePaymentRequest> payments = Collections.emptyList();

        UpdateSellerStatusRequest request1 = new UpdateSellerStatusRequest(code, payments);
        UpdateSellerStatusRequest request2 = new UpdateSellerStatusRequest(code, payments);

        assertEquals(request1, request2);
    }

    @Test
    void shouldNotBeEqualWithDifferentCode() {
        UpdateSellerStatusRequest request1 = new UpdateSellerStatusRequest("SELLER123", Collections.emptyList());
        UpdateSellerStatusRequest request2 = new UpdateSellerStatusRequest("SELLER456", Collections.emptyList());

        assertNotEquals(request1, request2);
    }

    @Test
    void shouldNotBeEqualWithDifferentPayments() {
        UpdateSellerStatusRequest request1 = new UpdateSellerStatusRequest("SELLER123", Collections.emptyList());
        UpdateSellerStatusRequest request2 = new UpdateSellerStatusRequest("SELLER123",
                List.of(new CreatePaymentRequest("123456", BigDecimal.valueOf(100.0))));

        assertNotEquals(request1, request2);
    }

    @Test
    void shouldGetCode() {
        String code = "SELLER123";
        UpdateSellerStatusRequest updateSellerStatusRequest = new UpdateSellerStatusRequest(code, Collections.emptyList());

        assertEquals(code, updateSellerStatusRequest.code());
    }

    @Test
    void shouldGetPayments() {
        List<CreatePaymentRequest> payments = List.of(new CreatePaymentRequest("123456", BigDecimal.valueOf(100.0)));
        UpdateSellerStatusRequest updateSellerStatusRequest = new UpdateSellerStatusRequest("SELLER123", payments);

        assertEquals(payments, updateSellerStatusRequest.payments());
    }
}
