package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;

import java.math.BigDecimal;

public record PaymentResponse(String billingCode, BigDecimal value, PaymentStatus paymentStatus) {
}
