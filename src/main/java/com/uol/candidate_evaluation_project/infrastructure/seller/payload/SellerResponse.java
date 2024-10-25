package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.domain.payment.Payment;

import java.util.List;

public record SellerResponse(String code, List<Payment> paymentResponses) {
}
