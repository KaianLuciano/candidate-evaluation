package com.uol.candidate_evaluation_project.domain.seller;

import com.uol.candidate_evaluation_project.domain.payment.Payment;

import java.util.List;

public record Seller(String code, List<Payment> payments) {
}
