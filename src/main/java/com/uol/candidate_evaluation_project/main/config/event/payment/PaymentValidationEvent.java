package com.uol.candidate_evaluation_project.main.config.event.payment;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;

public record PaymentValidationEvent(Payment payment, PaymentStatus status) {
}
