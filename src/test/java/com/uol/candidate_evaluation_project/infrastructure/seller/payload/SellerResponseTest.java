package com.uol.candidate_evaluation_project.infrastructure.seller.payload;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SellerResponseTest {

    @Test
    void shouldGenerateEquals() {
        String code = "SELLER123";
        List<Payment> paymentResponses = Collections.emptyList();

        SellerResponse sellerResponse1 = new SellerResponse(code, paymentResponses);
        SellerResponse sellerResponse2 = new SellerResponse(code, paymentResponses);

        assertEquals(sellerResponse1, sellerResponse2);
    }

    @Test
    void shouldNotBeEqualWithDifferentCode() {
        SellerResponse sellerResponse1 = new SellerResponse("SELLER123", Collections.emptyList());
        SellerResponse sellerResponse2 = new SellerResponse("SELLER456", Collections.emptyList());

        assertNotEquals(sellerResponse1, sellerResponse2);
    }

    @Test
    void shouldNotBeEqualWithDifferentPayments() {
        SellerResponse sellerResponse1 = new SellerResponse("SELLER123", Collections.emptyList());
        SellerResponse sellerResponse2 = new SellerResponse("SELLER123", List.of(new Payment("123456", BigDecimal.valueOf(100.0), null))); // Adjust payment object accordingly

        assertNotEquals(sellerResponse1, sellerResponse2);
    }

    @Test
    void shouldGetCode() {
        String code = "SELLER123";
        SellerResponse sellerResponse = new SellerResponse(code, Collections.emptyList());

        assertEquals(code, sellerResponse.code());
    }

    @Test
    void shouldGetPayments() {
        List<Payment> paymentResponses = List.of(new Payment("123456", BigDecimal.valueOf(100.0), null)); // Adjust payment object accordingly
        SellerResponse sellerResponse = new SellerResponse("SELLER123", paymentResponses);

        assertEquals(paymentResponses, sellerResponse.paymentResponses());
    }
}
