package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UpdateSellerResponseTest {

    @Test
    void shouldGenerateEquals() {
        String code = "SELLER123";
        List<Payment> payments = Collections.emptyList();

        UpdateSellerResponse response1 = new UpdateSellerResponse(code, payments);
        UpdateSellerResponse response2 = new UpdateSellerResponse(code, payments);

        assertEquals(response1, response2);
    }

    @Test
    void shouldNotBeEqualWithDifferentCode() {
        UpdateSellerResponse response1 = new UpdateSellerResponse("SELLER123", Collections.emptyList());
        UpdateSellerResponse response2 = new UpdateSellerResponse("SELLER456", Collections.emptyList());

        assertNotEquals(response1, response2);
    }

    @Test
    void shouldNotBeEqualWithDifferentPayments() {
        UpdateSellerResponse response1 = new UpdateSellerResponse("SELLER123", Collections.emptyList());
        UpdateSellerResponse response2 = new UpdateSellerResponse("SELLER123",
                List.of(new Payment("123456", BigDecimal.valueOf(100.0), null)));

        assertNotEquals(response1, response2);
    }

    @Test
    void shouldGetCode() {
        String code = "SELLER123";
        UpdateSellerResponse updateSellerResponse = new UpdateSellerResponse(code, Collections.emptyList());

        assertEquals(code, updateSellerResponse.code());
    }

    @Test
    void shouldGetPayments() {
        List<Payment> payments = List.of(new Payment("123456", BigDecimal.valueOf(100.0), null));
        UpdateSellerResponse updateSellerResponse = new UpdateSellerResponse("SELLER123", payments);

        assertEquals(payments, updateSellerResponse.payments());
    }
}
