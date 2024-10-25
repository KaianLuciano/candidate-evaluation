package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CreatePaymentResponseTest {

    @Test
    void shouldCreateResponseWithValidParameters() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(100.00);

        CreatePaymentResponse response = new CreatePaymentResponse(billingCode, value);

        assertEquals(billingCode, response.billingCode());
        assertEquals(value, response.value());
    }

    @Test
    void shouldCreateResponseWithDifferentValues() {
        String billingCode1 = "123456";
        BigDecimal value1 = BigDecimal.valueOf(100.00);
        String billingCode2 = "654321";
        BigDecimal value2 = BigDecimal.valueOf(200.00);

        CreatePaymentResponse response1 = new CreatePaymentResponse(billingCode1, value1);
        CreatePaymentResponse response2 = new CreatePaymentResponse(billingCode2, value2);

        assertNotEquals(response1, response2);
    }

    /*@Test
    void shouldReturnCorrectHashCode() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(100.00);
        CreatePaymentResponse response = new CreatePaymentResponse(billingCode, value);

        int expectedHashCode = billingCode.hashCode() + value.hashCode();
        assertEquals(expectedHashCode, response.hashCode());
    }*/

    @Test
    void shouldEqualAnotherResponseWithSameValues() {
        String billingCode = "123456";
        BigDecimal value = BigDecimal.valueOf(100.00);
        CreatePaymentResponse response1 = new CreatePaymentResponse(billingCode, value);
        CreatePaymentResponse response2 = new CreatePaymentResponse(billingCode, value);

        assertEquals(response1, response2);
    }

    @Test
    void shouldNotEqualAnotherResponseWithDifferentValues() {
        CreatePaymentResponse response1 = new CreatePaymentResponse("123456", BigDecimal.valueOf(100.00));
        CreatePaymentResponse response2 = new CreatePaymentResponse("654321", BigDecimal.valueOf(200.00));

        assertNotEquals(response1, response2);
    }
}
