package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntityMapper;

public class SellerEntityMapper {
    private final PaymentEntityMapper paymentEntityMapper;

    public SellerEntityMapper(PaymentEntityMapper paymentEntityMapper) {
        this.paymentEntityMapper = paymentEntityMapper;
    }

    SellerEntity toEntity(Seller sellerDomainObj) {
        return new SellerEntity(sellerDomainObj.code());
    }

    Seller toDomainObj(SellerEntity sellerEntity) {
        return new Seller(sellerEntity.getCode(), sellerEntity.getPayments().stream()
                .map(paymentEntityMapper::toDomainObj).toList());
    }
}
