package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import java.math.BigDecimal;

public record CreatePaymentRequest(String billingCode, BigDecimal value) {
}
