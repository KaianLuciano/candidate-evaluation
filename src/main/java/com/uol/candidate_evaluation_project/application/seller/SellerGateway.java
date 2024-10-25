package com.uol.candidate_evaluation_project.application.seller;

import com.uol.candidate_evaluation_project.domain.seller.Seller;

public interface SellerGateway {
    Seller create(Seller seller);

    Seller findById(String sellerCode);

    boolean existsById(String sellerCode);

    Seller update(Seller seller);

    void delete(String sellerCode);
}
