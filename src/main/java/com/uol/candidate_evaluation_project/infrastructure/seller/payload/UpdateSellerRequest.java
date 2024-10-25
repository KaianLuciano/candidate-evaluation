package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.domain.payment.Payment;

import java.util.List;

public record UpdateSellerRequest(String code, List<Payment> payments) {
}
