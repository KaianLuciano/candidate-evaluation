package com.uol.candidate_evaluation_project.infrastructure.payment.payload;

import java.math.BigDecimal;

public record CreatePaymentResponse(String billingCode, BigDecimal value) {
}
