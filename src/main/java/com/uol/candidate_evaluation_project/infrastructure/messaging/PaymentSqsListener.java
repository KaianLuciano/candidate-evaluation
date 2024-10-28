package com.uol.candidate_evaluation_project.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentSqsListener {
    private final PaymentGateway paymentGateway;
    private final ObjectMapper objectMapper;

    public PaymentSqsListener(PaymentGateway paymentGateway, ObjectMapper objectMapper) {
        this.paymentGateway = paymentGateway;
        this.objectMapper = objectMapper;
    }

    @SqsListener("partial-queue-payment")
    public void receivePartialPayment(String message) {
        processPayment(message);
    }

    @SqsListener("full-payments-payload")
    public void receiveFullPayment(String message) {
        processPayment(message);
    }

    @SqsListener("excess-payments-payload")
    public void receiveExcessPayment(String message) {
        processPayment(message);
    }

    private void processPayment(String message) {
        try {
            Payment payment = objectMapper.readValue(message, Payment.class);
            Payment paymentSaved = paymentGateway.updateStatus(payment);
            System.out.println("PaymentUpdated !" + paymentSaved);
        } catch (Exception e) {
            System.err.println("Erro ao processar pagamento: " + e.getMessage());
        }
    }
}
