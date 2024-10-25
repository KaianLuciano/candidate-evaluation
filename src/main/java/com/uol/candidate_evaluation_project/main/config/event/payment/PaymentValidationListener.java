package com.uol.candidate_evaluation_project.main.config.event.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.infrastructure.messaging.MessagingService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidationListener {
    private final MessagingService messagingService;
    private final ObjectMapper objectMapper;

    public PaymentValidationListener(MessagingService messagingService, ObjectMapper objectMapper) {
        this.messagingService = messagingService;
        this.objectMapper = objectMapper;
    }

    @EventListener
    public void handlePaymentValidationEvent(PaymentValidationEvent event) {
        Payment payment = event.payment();
        PaymentStatus status = event.status();

        Payment paymentUpdate = new Payment(payment.billingCode(), payment.value(), status);

        String queueName = getQueueName(status);

        try {
            messagingService.sendMessage(queueName, objectMapper.writeValueAsString(paymentUpdate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getQueueName(PaymentStatus status) {
        return switch (status) {
            case PARTIAL -> "https://sqs.us-east-1.amazonaws.com/891376922401/partial-queue-payment";
            case FULL -> "https://sqs.us-east-1.amazonaws.com/891376922401/full-payments-payload";
            case EXCESS -> "https://sqs.us-east-1.amazonaws.com/891376922401/excess-payments-payload";
            default -> throw new IllegalArgumentException("Unknown payment status: " + status);
        };
    }
}