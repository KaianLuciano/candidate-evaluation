package com.uol.candidate_evaluation_project.application.payment;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentUseCaseTest {

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private PaymentUseCase paymentUseCase;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        payment = new Payment("billingCode123", new BigDecimal("100.00"), PaymentStatus.EXCESS);
    }

    @Test
    @DisplayName("Test create Payment")
    void testCreatePayment() {
        when(paymentGateway.create(any(Payment.class))).thenReturn(payment);

        Payment createdPayment = paymentUseCase.create(payment);

        verify(paymentGateway, times(1)).create(payment);
        assertSame(createdPayment, payment);
    }

    @Test
    @DisplayName("Test find Payment by ID")
    void testFindPaymentById() {
        when(paymentGateway.findById(payment.billingCode())).thenReturn(payment);

        Payment foundPayment = paymentUseCase.findById(payment.billingCode());

        verify(paymentGateway, times(1)).findById(payment.billingCode());
        assertSame(foundPayment, payment);
    }

    @Test
    @DisplayName("Test update Payment")
    void testUpdatePayment() {
        Payment updatedPayment = new Payment("billingCode123", new BigDecimal("150.00"),
                PaymentStatus.EXCESS);
        when(paymentGateway.update(any(String.class), any(Payment.class))).thenReturn(updatedPayment);

        Payment result = paymentUseCase.update(payment.billingCode(), payment);

        verify(paymentGateway, times(1)).update(payment.billingCode(), payment);
        assertSame(result, updatedPayment);
    }

    @Test
    @DisplayName("Test delete Payment")
    void testDeletePayment() {
        paymentUseCase.delete(payment.billingCode());

        verify(paymentGateway, times(1)).delete(payment.billingCode());
    }
}
