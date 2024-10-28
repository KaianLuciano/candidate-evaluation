package com.uol.candidate_evaluation_project.application.seller;

import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntity;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerEntity;

import java.util.List;

public interface SellerGateway {
    Seller create(SellerEntity sellerEntity, List<PaymentEntity> payments);

    Seller findById(String sellerCode);

    boolean existsById(String sellerCode);

    Seller update(Seller seller);

    void delete(String sellerCode);
}
