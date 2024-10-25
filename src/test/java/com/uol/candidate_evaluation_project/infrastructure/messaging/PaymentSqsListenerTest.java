package com.uol.candidate_evaluation_project.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class PaymentSqsListenerTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PaymentSqsListener paymentSqsListener;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        payment = new Payment("billingCode123", new BigDecimal("100.00"), PaymentStatus.EXCESS);
    }

    @Test
    @DisplayName("Test receivePartialPayment method")
    void testReceivePartialPayment() throws Exception {
        String message = "{\"billingCode\": \"billingCode123\", \"value\": 100.00}";

        when(objectMapper.readValue(message, Payment.class)).thenReturn(payment);
        when(paymentGateway.updateStatus(payment)).thenReturn(payment);

        paymentSqsListener.receivePartialPayment(message);

        verify(objectMapper, times(1)).readValue(message, Payment.class);
        verify(paymentGateway, times(1)).updateStatus(payment);
    }

    @Test
    @DisplayName("Test receiveFullPayment method")
    void testReceiveFullPayment() throws Exception {
        String message = "{\"billingCode\": \"billingCode123\", \"value\": 100.00}";

        when(objectMapper.readValue(message, Payment.class)).thenReturn(payment);
        when(paymentGateway.updateStatus(payment)).thenReturn(payment);

        paymentSqsListener.receiveFullPayment(message);

        verify(objectMapper, times(1)).readValue(message, Payment.class);
        verify(paymentGateway, times(1)).updateStatus(payment);
    }

    @Test
    @DisplayName("Test receiveExcessPayment method")
    void testReceiveExcessPayment() throws Exception {
        String message = "{\"billingCode\": \"billingCode123\", \"value\": 100.00}";

        when(objectMapper.readValue(message, Payment.class)).thenReturn(payment);
        when(paymentGateway.updateStatus(payment)).thenReturn(payment);

        paymentSqsListener.receiveExcessPayment(message);

        verify(objectMapper, times(1)).readValue(message, Payment.class);
        verify(paymentGateway, times(1)).updateStatus(payment);
    }

    @Test
    @DisplayName("Test processPayment with exception")
    void testProcessPaymentWithException() throws Exception {
        String message = "{\"billingCode\": \"billingCode123\", \"value\": 100.00}";

        when(objectMapper.readValue(message, Payment.class)).thenThrow(new RuntimeException("Parse error"));

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputStream);
        System.setOut(newOut);

        paymentSqsListener.receivePartialPayment(message);

        System.setOut(originalOut);

        String output = outputStream.toString();
        assertFalse(output.contains("Erro ao processar pagamento: Parse error"));
    }
}

