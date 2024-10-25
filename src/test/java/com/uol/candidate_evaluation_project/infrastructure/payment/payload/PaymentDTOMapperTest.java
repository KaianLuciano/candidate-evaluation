package com.uol.candidate_evaluation_project.infrastructure.payment.payload;


import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentDTOMapperTest {

    private final PaymentDTOMapper mapper = new PaymentDTOMapper();

    @Test
    void shouldMapToCreateResponse() {
        Payment payment = new Payment("123456", BigDecimal.valueOf(100.00), null);

        CreatePaymentResponse response = mapper.toCreateResponse(payment);

        assertEquals(payment.billingCode(), response.billingCode());
        assertEquals(payment.value(), response.value());
    }

    @Test
    void shouldMapToUpdateResponse() {
        Payment payment = new Payment("123456", BigDecimal.valueOf(100.00), PaymentStatus.PARTIAL);

        UpdatePaymentResponse response = mapper.toUpdateResponse(payment);

        assertEquals(payment.billingCode(), response.billingCode());
        assertEquals(payment.value(), response.value());
        assertEquals(payment.status(), response.paymentStatus());
    }

    @Test
    void shouldMapToResponse() {
        Payment payment = new Payment("123456", BigDecimal.valueOf(100.00), PaymentStatus.PARTIAL);

        PaymentResponse response = mapper.toResponse(payment);

        assertEquals(payment.billingCode(), response.billingCode());
        assertEquals(payment.value(), response.value());
        assertEquals(payment.status(), response.paymentStatus());
    }

    @Test
    void shouldMapToPaymentFromCreateRequest() {
        CreatePaymentRequest request = new CreatePaymentRequest("123456", BigDecimal.valueOf(100.00));

        Payment payment = mapper.toPayment(request);

        assertEquals(request.billingCode(), payment.billingCode());
        assertEquals(request.value(), payment.value());
        assertNull(payment.status());
    }

    @Test
    void shouldMapToPaymentFromUpdateRequest() {
        UpdatePaymentRequest request = new UpdatePaymentRequest("123456", BigDecimal.valueOf(100.00),
                PaymentStatus.PARTIAL);

        Payment payment = mapper.toPayment(request);

        assertEquals(request.billingCode(), payment.billingCode());
        assertEquals(request.value(), payment.value());
        assertEquals(request.paymentStatus(), payment.status());
    }
}
