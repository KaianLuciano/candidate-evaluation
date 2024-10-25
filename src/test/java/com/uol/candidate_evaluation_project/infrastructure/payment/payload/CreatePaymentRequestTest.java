package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatePaymentRequestTest {

    @Test
    void shouldCreateRequestWithValidParameters() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(100.00);

        CreatePaymentRequest request = new CreatePaymentRequest(billingCode, value);

        assertEquals(billingCode, request.billingCode());
        assertEquals(value, request.value());
    }

    /*@Test
    void shouldThrowExceptionWhenBillingCodeIsNull() {
        BigDecimal value = BigDecimal.valueOf(100.00);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreatePaymentRequest(null, value);
        });

        assertEquals("Billing code must not be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBillingCodeIsEmpty() {
        BigDecimal value = BigDecimal.valueOf(100.00);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreatePaymentRequest("", value);
        });

        assertEquals("Billing code must not be null or empty", exception.getMessage());
    }*/

    /*@Test
    void shouldThrowExceptionWhenValueIsNull() {
        String billingCode = "123456";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreatePaymentRequest(billingCode, null);
        });

        assertEquals("Value must be greater than zero", exception.getMessage());
    }*/

    /*@Test
    void shouldThrowExceptionWhenValueIsZeroOrNegative() {
        String billingCode = "123456";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreatePaymentRequest(billingCode, BigDecimal.ZERO);
        });

        assertEquals("Value must be greater than zero", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreatePaymentRequest(billingCode, BigDecimal.valueOf(-10));
        });

        assertEquals("Value must be greater than zero", exception.getMessage());
    }*/
}
