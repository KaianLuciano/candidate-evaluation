package com.uol.candidate_evaluation_project.infrastructure.payment;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PaymentEntityTest {

    @Test
    void testGettersAndSetters() {
        PaymentEntity paymentEntity = new PaymentEntity();
        String billingCode = "12345";
        BigDecimal value = BigDecimal.valueOf(100.00);
        PaymentStatus status = PaymentStatus.FULL;
        SellerEntity seller = new SellerEntity(); // Assume a valid SellerEntity is created

        paymentEntity.setBillingCode(billingCode);
        paymentEntity.setValue(value);
        paymentEntity.setStatus(status);
        paymentEntity.setSeller(seller);

        assertEquals(billingCode, paymentEntity.getBillingCode());
        assertEquals(value, paymentEntity.getValue());
        assertEquals(status, paymentEntity.getStatus());
        assertEquals(seller, paymentEntity.getSeller());
    }

    @Test
    void testUpdate() {
        PaymentEntity paymentEntity = new PaymentEntity();
        Payment payment = new Payment("12345", BigDecimal.valueOf(200.00), PaymentStatus.PARTIAL); // Assume Payment has a suitable constructor

        paymentEntity.update(payment);

        assertEquals(payment.billingCode(), paymentEntity.getBillingCode());
        assertEquals(payment.value(), paymentEntity.getValue());
        assertEquals(payment.status(), paymentEntity.getStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        PaymentEntity paymentEntity1 = new PaymentEntity("12345", BigDecimal.valueOf(100.00),
                PaymentStatus.FULL);
        PaymentEntity paymentEntity2 = new PaymentEntity("12345", BigDecimal.valueOf(100.00),
                PaymentStatus.FULL);
        PaymentEntity paymentEntity3 = new PaymentEntity("67890", BigDecimal.valueOf(200.00),
                PaymentStatus.PARTIAL);

        assertEquals(paymentEntity1, paymentEntity2);
        assertNotEquals(paymentEntity1, paymentEntity3);

        assertEquals(paymentEntity1.hashCode(), paymentEntity2.hashCode());
        assertNotEquals(paymentEntity1.hashCode(), paymentEntity3.hashCode());
    }
}