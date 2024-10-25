package com.uol.candidate_evaluation_project.domain.seller;

import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SellerTest {
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        payment1 = new Payment("billingCode1", new BigDecimal("100.00"), PaymentStatus.PARTIAL);
        payment2 = new Payment("billingCode2", new BigDecimal("200.00"), PaymentStatus.FULL);
    }

    @Test
    @DisplayName("Test creating Seller with valid code and payments list")
    void testValidSellerCreation() {
        Seller seller = new Seller("seller123", List.of(payment1, payment2));
        assertEquals("seller123", seller.code());
        assertEquals(List.of(payment1, payment2), seller.payments());
    }

    /*@Test
    @DisplayName("Test NullPointerException for null code")
    void testSellerWithNullCode() {
        assertThrows(NullPointerException.class, () -> new Seller(null, List.of(payment1, payment2)));
    }

    @Test
    @DisplayName("Test NullPointerException for null payments list")
    void testSellerWithNullPaymentsList() {
        assertThrows(NullPointerException.class, () -> new Seller("seller123", null));
    }*/

    @Test
    @DisplayName("Test creating Seller with empty payments list")
    void testSellerWithEmptyPaymentsList() {
        Seller seller = new Seller("seller123", new ArrayList<>());
        assertEquals("seller123", seller.code());
        assertTrue(seller.payments().isEmpty());
    }

    @Test
    @DisplayName("Test creating Seller with valid single payment in list")
    void testSellerWithSinglePayment() {
        Seller seller = new Seller("seller123", List.of(payment1));
        assertEquals("seller123", seller.code());
        assertEquals(1, seller.payments().size());
        assertEquals(payment1, seller.payments().get(0));
    }

    @Test
    @DisplayName("Test equality of two identical Seller records")
    void testSellerEquality() {
        Seller seller1 = new Seller("seller123", List.of(payment1, payment2));
        Seller seller2 = new Seller("seller123", List.of(payment1, payment2));
        assertEquals(seller1, seller2);
    }

    @Test
    @DisplayName("Test toString output")
    void testSellerToString() {
        Seller seller = new Seller("seller123", List.of(payment1));
        String expected = "Seller[code=seller123, payments=[" + payment1.toString() + "]]";
        assertEquals(expected, seller.toString());
    }
}
