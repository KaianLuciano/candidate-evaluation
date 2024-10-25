package com.uol.candidate_evaluation_project.infrastructure.seller;


import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SellerEntityTest {

    @Test
    void shouldGenerateEquals() {
        String sellerCode = "SELLER123";
        List<PaymentEntity> payments = Collections.emptyList();

        SellerEntity seller1 = new SellerEntity(sellerCode);
        seller1.getPayments().addAll(payments);

        SellerEntity seller2 = new SellerEntity(sellerCode);
        seller2.getPayments().addAll(payments);

        assertEquals(seller1, seller2);
    }

    @Test
    void shouldNotBeEqualWithDifferentSellerCode() {
        SellerEntity seller1 = new SellerEntity("SELLER123");
        SellerEntity seller2 = new SellerEntity("SELLER456");

        assertNotEquals(seller1, seller2);
    }

    @Test
    void shouldGetCode() {
        String sellerCode = "SELLER123";
        SellerEntity sellerEntity = new SellerEntity(sellerCode);

        assertEquals(sellerCode, sellerEntity.getCode());
    }

    @Test
    void shouldSetCode() {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setCode("NEW_SELLER_CODE");

        assertEquals("NEW_SELLER_CODE", sellerEntity.getCode());
    }

    @Test
    void shouldGetPayments() {
        SellerEntity sellerEntity = new SellerEntity("SELLER123");
        List<PaymentEntity> payments = List.of(new PaymentEntity());
        sellerEntity.getPayments().addAll(payments);

        assertEquals(payments, sellerEntity.getPayments());
    }
}
