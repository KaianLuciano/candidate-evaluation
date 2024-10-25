package com.uol.candidate_evaluation_project.infrastructure.payment;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentEntityMapperTest {

    private final PaymentEntityMapper paymentEntityMapper = new PaymentEntityMapper();

    @Test
    void testToEntity() {
        Payment payment = new Payment("12345", BigDecimal.valueOf(100.00), PaymentStatus.FULL);

        PaymentEntity paymentEntity = paymentEntityMapper.toEntity(payment);

        assertEquals(payment.billingCode(), paymentEntity.getBillingCode());
        assertEquals(payment.value(), paymentEntity.getValue());
        assertEquals(payment.status(), paymentEntity.getStatus());
    }

    @Test
    void testToDomainObj() {
        PaymentEntity paymentEntity = new PaymentEntity("12345", BigDecimal.valueOf(100.00),
                PaymentStatus.FULL);

        Payment payment = paymentEntityMapper.toDomainObj(paymentEntity);

        assertEquals(paymentEntity.getBillingCode(), payment.billingCode());
        assertEquals(paymentEntity.getValue(), payment.value());
        assertEquals(paymentEntity.getStatus(), payment.status());
    }
}
