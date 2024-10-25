package com.uol.candidate_evaluation_project.infrastructure.payment;

import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.main.error.Errors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@Transactional
class PaymentRepositoryGatewayTest {

    @MockBean
    private PaymentRepository paymentRepository;

    private PaymentEntityMapper paymentEntityMapper;
    private PaymentGateway paymentGateway;

    @BeforeEach
    void setUp() {
        paymentEntityMapper = new PaymentEntityMapper();
        paymentGateway = new PaymentRepositoryGateway(paymentRepository, paymentEntityMapper);
    }

    @Test
    void testCreate() {
        Payment payment = new Payment("12345", BigDecimal.valueOf(100.00), PaymentStatus.FULL);
        PaymentEntity paymentEntity = paymentEntityMapper.toEntity(payment);

        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(paymentEntity);

        Payment createdPayment = paymentGateway.create(payment);

        assertEquals(payment.billingCode(), createdPayment.billingCode());
        assertEquals(payment.value(), createdPayment.value());
        assertEquals(payment.status(), createdPayment.status());
    }

    @Test
    void testFindById() {
        PaymentEntity paymentEntity = new PaymentEntity("12345", BigDecimal.valueOf(100.00), PaymentStatus.FULL);
        when(paymentRepository.findById("12345")).thenReturn(Optional.of(paymentEntity));

        Payment foundPayment = paymentGateway.findById("12345");

        assertEquals(paymentEntity.getBillingCode(), foundPayment.billingCode());
        assertEquals(paymentEntity.getValue(), foundPayment.value());
        assertEquals(paymentEntity.getStatus(), foundPayment.status());
    }

    @Test
    void testFindByIdNotFound() {
        when(paymentRepository.findById("99999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Errors.ResourceNotFoundException.class, () -> {
            paymentGateway.findById("99999");
        });
        assertEquals("Payment not found with code: 99999", exception.getMessage());
    }

    @Test
    void testUpdate() {
        PaymentEntity paymentEntity = new PaymentEntity("12345", BigDecimal.valueOf(100.00), PaymentStatus.FULL);
        when(paymentRepository.findById("12345")).thenReturn(Optional.of(paymentEntity));

        Payment updatedPayment = new Payment("12345", BigDecimal.valueOf(150.00), PaymentStatus.PARTIAL);
        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(paymentEntityMapper.toEntity(updatedPayment));

        Payment result = paymentGateway.update("12345", updatedPayment);

        assertEquals(updatedPayment.billingCode(), result.billingCode());
        assertEquals(updatedPayment.value(), result.value());
        assertEquals(updatedPayment.status(), result.status());
    }

    @Test
    void testUpdateNotFound() {
        when(paymentRepository.findById("99999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Errors.ResourceNotFoundException.class, () -> {
            paymentGateway.update("99999", new Payment("99999", BigDecimal.valueOf(100.00), PaymentStatus.FULL));
        });
        assertEquals("Payment not found with code: 99999", exception.getMessage());
    }

    @Test
    void testDelete() {
        PaymentEntity paymentEntity = new PaymentEntity("12345", BigDecimal.valueOf(100.00), PaymentStatus.FULL);
        when(paymentRepository.findById("12345")).thenReturn(Optional.of(paymentEntity));

        paymentGateway.delete("12345");

        verify(paymentRepository, times(1)).delete(paymentEntity);
    }

    @Test
    void testDeleteNotFound() {
        when(paymentRepository.findById("99999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Errors.ResourceNotFoundException.class, () -> {
            paymentGateway.delete("99999");
        });
        assertEquals("Payment not found with code: 99999", exception.getMessage());
    }
}
