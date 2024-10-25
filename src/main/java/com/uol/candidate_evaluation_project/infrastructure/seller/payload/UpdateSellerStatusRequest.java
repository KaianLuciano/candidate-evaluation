package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.infrastructure.payment.payload.CreatePaymentRequest;

import java.util.List;

public record UpdateSellerStatusRequest(String code, List<CreatePaymentRequest> payments) {
}
