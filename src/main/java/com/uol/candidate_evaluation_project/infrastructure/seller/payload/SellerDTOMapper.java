package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.payment.payload.PaymentDTOMapper;

public class SellerDTOMapper {
    private final PaymentDTOMapper paymentDTOMapper;

    public SellerDTOMapper(PaymentDTOMapper paymentDTOMapper) {
        this.paymentDTOMapper = paymentDTOMapper;
    }

    public CreateSellerResponse toCreateResponse(Seller seller) {
        return new CreateSellerResponse(seller.code());
    }

    public UpdateSellerResponse toUpdateResponse(Seller seller) {
        return new UpdateSellerResponse(seller.code(), seller.payments());
    }

    public SellerResponse toResponse(Seller seller) {
        return new SellerResponse(seller.code(), seller.payments());
    }

    public Seller toSeller(CreateSellerRequest createSellerRequest) {
        return new Seller(createSellerRequest.code(), null);
    }

    public Seller toSeller(UpdateSellerRequest updateSellerRequest) {
        return new Seller(updateSellerRequest.code(), updateSellerRequest.payments());
    }

    public Seller toSeller(UpdateSellerStatusRequest updateSellerStatusRequest) {
        return new Seller(updateSellerStatusRequest.code(), updateSellerStatusRequest.payments()
                .stream().map(paymentDTOMapper::toPayment).toList());
    }
}
