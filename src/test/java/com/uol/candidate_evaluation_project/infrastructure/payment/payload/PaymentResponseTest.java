package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PaymentResponseTest {

    @Test
    void shouldCreatePaymentResponse() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(100.00);
        PaymentStatus paymentStatus = PaymentStatus.PARTIAL;

        PaymentResponse paymentResponse = new PaymentResponse(billingCode, value, paymentStatus);

        assertEquals(billingCode, paymentResponse.billingCode());
        assertEquals(value, paymentResponse.value());
        assertEquals(paymentStatus, paymentResponse.paymentStatus());
    }

    @Test
    void shouldHaveCorrectToString() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(100.00);
        PaymentStatus paymentStatus = PaymentStatus.PARTIAL;

        PaymentResponse paymentResponse = new PaymentResponse(billingCode, value, paymentStatus);

        String expectedString = "PaymentResponse[billingCode=123456, value=100.0, paymentStatus=PARTIAL]";
        assertEquals(expectedString, paymentResponse.toString());
    }

    @Test
    void shouldCheckEquality() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(100.00);
        PaymentStatus paymentStatus = PaymentStatus.PARTIAL;

        PaymentResponse paymentResponse1 = new PaymentResponse(billingCode, value, paymentStatus);
        PaymentResponse paymentResponse2 = new PaymentResponse(billingCode, value, paymentStatus);
        PaymentResponse paymentResponse3 = new PaymentResponse("654321", value, paymentStatus);

        assertEquals(paymentResponse1, paymentResponse2);
        assertNotEquals(paymentResponse1, paymentResponse3);
    }
}
