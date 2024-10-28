package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import java.util.List;

public record CreateSellerRequest(String code, List<String> paymentsCodes) {
}
