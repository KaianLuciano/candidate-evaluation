package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntity;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SellerEntityMapperTest {

    private PaymentEntityMapper paymentEntityMapper;
    private SellerEntityMapper sellerEntityMapper;

    @BeforeEach
    void setUp() {
        paymentEntityMapper = mock(PaymentEntityMapper.class);
        sellerEntityMapper = new SellerEntityMapper(paymentEntityMapper);
    }

    @Test
    void shouldMapToEntity() {
        Seller seller = new Seller("SELLER123", Collections.emptyList());

        SellerEntity sellerEntity = sellerEntityMapper.toEntity(seller);

        assertEquals(seller.code(), sellerEntity.getCode());
        assertTrue(sellerEntity.getPayments().isEmpty());
    }

    @Test
    void shouldMapToDomainObj() {
        SellerEntity sellerEntity = new SellerEntity("SELLER123");
        when(paymentEntityMapper.toDomainObj(any()))
                .thenReturn(new Payment("teste1", BigDecimal.valueOf(2.2), PaymentStatus.EXCESS));

        Seller seller = sellerEntityMapper.toDomainObj(sellerEntity);

        assertEquals(sellerEntity.getCode(), seller.code());
        assertEquals(sellerEntity.getPayments().size(), seller.payments().size());
    }

    @Test
    void shouldMapPaymentsToDomainObj() {
        SellerEntity sellerEntity = new SellerEntity("SELLER123");
        PaymentEntity paymentEntity = new PaymentEntity();
        sellerEntity.getPayments().add(paymentEntity);

        when(paymentEntityMapper.toDomainObj(paymentEntity))
                .thenReturn(new Payment("teste1", BigDecimal.valueOf(2.2), PaymentStatus.EXCESS));

        Seller seller = sellerEntityMapper.toDomainObj(sellerEntity);

        assertEquals(1, seller.payments().size());
    }
}
