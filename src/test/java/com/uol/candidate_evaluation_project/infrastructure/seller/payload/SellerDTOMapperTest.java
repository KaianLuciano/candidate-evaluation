package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.payment.payload.CreatePaymentRequest;
import com.uol.candidate_evaluation_project.infrastructure.payment.payload.PaymentDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SellerDTOMapperTest {

    private SellerDTOMapper sellerDTOMapper;
    private PaymentDTOMapper paymentDTOMapper;

    @BeforeEach
    void setUp() {
        paymentDTOMapper = new PaymentDTOMapper();
        sellerDTOMapper = new SellerDTOMapper(paymentDTOMapper);
    }

    @Test
    void shouldMapToCreateResponse() {
        Seller seller = new Seller("SELLER123", null);

        CreateSellerResponse response = sellerDTOMapper.toCreateResponse(seller);

        assertEquals(seller.code(), response.code());
    }

    @Test
    void shouldMapToUpdateResponse() {
        List<Payment> payments = Collections.emptyList();
        Seller seller = new Seller("SELLER123", payments);

        UpdateSellerResponse response = sellerDTOMapper.toUpdateResponse(seller);

        assertEquals(seller.code(), response.code());
        assertEquals(payments, response.payments());
    }

    @Test
    void shouldMapToResponse() {
        List<Payment> payments = Collections.emptyList();
        Seller seller = new Seller("SELLER123", payments);

        SellerResponse response = sellerDTOMapper.toResponse(seller);

        assertEquals(seller.code(), response.code());
        assertEquals(payments, response.paymentResponses());
    }

    @Test
    void shouldMapToSellerFromCreateRequest() {
        CreateSellerRequest request = new CreateSellerRequest("SELLER123", List.of());

        Seller seller = sellerDTOMapper.toSeller(request);

        assertEquals(request.code(), seller.code());
        assertNull(seller.payments());
    }

    @Test
    void shouldMapToSellerFromUpdateRequest() {
        List<Payment> payments = Collections.emptyList();
        UpdateSellerRequest request = new UpdateSellerRequest("SELLER123", payments);

        Seller seller = sellerDTOMapper.toSeller(request);

        assertEquals(request.code(), seller.code());
        assertEquals(payments, seller.payments());
    }

    @Test
    void shouldMapToSellerFromUpdateStatusRequest() {
        List<CreatePaymentRequest> payments = Collections.emptyList();
        UpdateSellerStatusRequest request = new UpdateSellerStatusRequest("SELLER123", payments);

        Seller seller = sellerDTOMapper.toSeller(request);

        assertEquals(request.code(), seller.code());
        assertEquals(payments, seller.payments());
    }
}
