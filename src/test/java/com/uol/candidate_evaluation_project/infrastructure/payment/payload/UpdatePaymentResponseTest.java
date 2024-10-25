package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UpdatePaymentResponseTest {

    @Test
    void shouldCreateUpdatePaymentResponse() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(200.0);
        PaymentStatus paymentStatus = PaymentStatus.PARTIAL;

        UpdatePaymentResponse response = new UpdatePaymentResponse(billingCode, value, paymentStatus);

        assertEquals(billingCode, response.billingCode());
        assertEquals(value, response.value());
        assertEquals(paymentStatus, response.paymentStatus());
    }

    @Test
    void shouldEqualSameObjects() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(200.0);
        PaymentStatus paymentStatus = PaymentStatus.PARTIAL;

        UpdatePaymentResponse response1 = new UpdatePaymentResponse(billingCode, value, paymentStatus);
        UpdatePaymentResponse response2 = new UpdatePaymentResponse(billingCode, value, paymentStatus);

        assertEquals(response1, response2);
    }

    @Test
    void shouldNotEqualDifferentObjects() {
        UpdatePaymentResponse response1 = new UpdatePaymentResponse("123456", BigDecimal.valueOf(200.0),
                PaymentStatus.PARTIAL);
        UpdatePaymentResponse response2 = new UpdatePaymentResponse("654321", BigDecimal.valueOf(300.0),
                PaymentStatus.FULL);

        assertNotEquals(response1, response2);
    }
}
