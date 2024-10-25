package com.uol.candidate_evaluation_project.domain.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    private static final String BILLING_CODE = "billingCode123";
    private static final BigDecimal VALUE = new BigDecimal("150.00");
    private static final PaymentStatus STATUS = PaymentStatus.PARTIAL;

    @Test
    @DisplayName("Test creating Payment with valid billing code, value, and status")
    void testValidPaymentCreation() {
        Payment payment = new Payment(BILLING_CODE, VALUE, STATUS);
        assertEquals(BILLING_CODE, payment.billingCode());
        assertEquals(VALUE, payment.value());
        assertEquals(STATUS, payment.status());
    }

    /*@Test
    @DisplayName("Test NullPointerException for null billing code")
    void testPaymentWithNullBillingCode() {
        assertThrows(NullPointerException.class, () -> new Payment(null, VALUE, STATUS));
    }

    @Test
    @DisplayName("Test NullPointerException for null value")
    void testPaymentWithNullValue() {
        assertThrows(NullPointerException.class, () -> new Payment(BILLING_CODE, null, STATUS));
    }

    @Test
    @DisplayName("Test NullPointerException for null status")
    void testPaymentWithNullStatus() {
        assertThrows(NullPointerException.class, () -> new Payment(BILLING_CODE, VALUE, null));
    }*/

    @Test
    @DisplayName("Test creating Payment with zero value")
    void testPaymentWithZeroValue() {
        Payment payment = new Payment(BILLING_CODE, BigDecimal.ZERO, STATUS);
        assertEquals(BigDecimal.ZERO, payment.value());
    }

    @Test
    @DisplayName("Test creating Payment with negative value")
    void testPaymentWithNegativeValue() {
        Payment payment = new Payment(BILLING_CODE, new BigDecimal("-100.00"), STATUS);
        assertEquals(new BigDecimal("-100.00"), payment.value());
    }

    @Test
    @DisplayName("Test equality of two identical Payment records")
    void testPaymentEquality() {
        Payment payment1 = new Payment(BILLING_CODE, VALUE, STATUS);
        Payment payment2 = new Payment(BILLING_CODE, VALUE, STATUS);
        assertEquals(payment1, payment2);
    }

    @Test
    @DisplayName("Test toString output")
    void testPaymentToString() {
        Payment payment = new Payment(BILLING_CODE, VALUE, STATUS);
        String expected = "Payment[billingCode=" + BILLING_CODE + ", value=" + VALUE + ", status=" + STATUS + "]";
        assertEquals(expected, payment.toString());
    }
}
