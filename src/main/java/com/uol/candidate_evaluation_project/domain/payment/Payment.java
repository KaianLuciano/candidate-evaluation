package com.uol.candidate_evaluation_project.domain.payment;

import java.math.BigDecimal;

public record Payment(String billingCode, BigDecimal value, PaymentStatus status) {
}
