package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UpdatePaymentRequestTest {

    @Test
    void shouldCreateUpdatePaymentRequest() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(150.0);
        PaymentStatus paymentStatus = PaymentStatus.FULL;

        UpdatePaymentRequest request = new UpdatePaymentRequest(billingCode, value, paymentStatus);

        assertEquals(billingCode, request.billingCode());
        assertEquals(value, request.value());
        assertEquals(paymentStatus, request.paymentStatus());
    }

    @Test
    void shouldEqualSameObjects() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(150.0);
        PaymentStatus paymentStatus = PaymentStatus.FULL;

        UpdatePaymentRequest request1 = new UpdatePaymentRequest(billingCode, value, paymentStatus);
        UpdatePaymentRequest request2 = new UpdatePaymentRequest(billingCode, value, paymentStatus);

        assertEquals(request1, request2);
    }

    @Test
    void shouldNotEqualDifferentObjects() {
        UpdatePaymentRequest request1 = new UpdatePaymentRequest("123456", BigDecimal.valueOf(150.0),
                PaymentStatus.FULL);
        UpdatePaymentRequest request2 = new UpdatePaymentRequest("654321", BigDecimal.valueOf(200.0),
                PaymentStatus.PARTIAL);

        assertNotEquals(request1, request2);
    }
}
