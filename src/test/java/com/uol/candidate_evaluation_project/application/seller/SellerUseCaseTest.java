package com.uol.candidate_evaluation_project.application.seller;

import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerEntity;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerEntityMapper;
import com.uol.candidate_evaluation_project.main.config.event.payment.PaymentValidationEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SellerUseCaseTest {

    @Mock
    private SellerGateway sellerGateway;

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private SellerUseCase sellerUseCase;

    private SellerEntityMapper sellerEntityMapper;

    private Seller seller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seller = new Seller("sellerCode123", Collections.emptyList());
    }

    @Test
    @DisplayName("Test create Seller")
    void testCreateSeller() {
        when(sellerGateway.create(any(SellerEntity.class), any())).thenReturn(seller);

        Seller createdSeller = sellerUseCase.create(seller, List.of("Stringf"));

        verify(sellerGateway, times(1)).create(sellerEntityMapper.toEntity(seller),
                List.of());
        assertSame(createdSeller, seller);
    }

    @Test
    @DisplayName("Test find Seller by ID")
    void testFindSellerById() {
        when(sellerGateway.findById(seller.code())).thenReturn(seller);

        Seller foundSeller = sellerUseCase.findById(seller.code());

        verify(sellerGateway, times(1)).findById(seller.code());
        assertSame(foundSeller, seller);
    }

    @Test
    @DisplayName("Test update Seller")
    void testUpdateSeller() {
        Seller updatedSeller = new Seller("sellerCode123", Collections.emptyList());
        when(sellerGateway.findById(seller.code())).thenReturn(seller);
        when(sellerGateway.update(any(Seller.class))).thenReturn(updatedSeller);

        Seller result = sellerUseCase.update(seller.code(), seller);

        verify(sellerGateway, times(1)).findById(seller.code());
        verify(sellerGateway, times(1)).update(seller);
        assertSame(result, updatedSeller);
    }

    @Test
    @DisplayName("Test update Seller status with valid payments")
    void testUpdateStatusWithValidPayments() {
        Payment payment = new Payment("billingCode123", BigDecimal.valueOf(100), PaymentStatus.EXCESS);
        seller = new Seller("sellerCode123", Collections.singletonList(payment));
        when(sellerGateway.existsById(seller.code())).thenReturn(true);
        when(paymentGateway.existsById(payment.billingCode())).thenReturn(true);
        when(paymentGateway.findById(payment.billingCode())).thenReturn(payment);
        when(paymentGateway.findById(payment.billingCode())).thenReturn(payment);

        sellerUseCase.updateStatus(seller.code(), seller);

        ArgumentCaptor<PaymentValidationEvent> eventCaptor = ArgumentCaptor.forClass(PaymentValidationEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());

        PaymentValidationEvent capturedEvent = eventCaptor.getValue();
        assertSame(capturedEvent.payment(), payment);
        assertEquals(PaymentStatus.FULL, capturedEvent.status());
    }

    @Test
    @DisplayName("Test delete Seller")
    void testDeleteSeller() {
        sellerUseCase.delete(seller.code());

        verify(sellerGateway, times(1)).delete(seller.code());
    }

    @Test
    @DisplayName("Test validatePayment with partial amount")
    void testValidatePaymentPartial() {
        BigDecimal paymentAmount = BigDecimal.valueOf(50);
        BigDecimal originalAmount = BigDecimal.valueOf(100);

        PaymentStatus status = sellerUseCase.validatePayment(paymentAmount, originalAmount);

        assertEquals(PaymentStatus.PARTIAL, status);
    }

    @Test
    @DisplayName("Test validatePayment with full amount")
    void testValidatePaymentFull() {
        BigDecimal paymentAmount = BigDecimal.valueOf(100);
        BigDecimal originalAmount = BigDecimal.valueOf(100);

        PaymentStatus status = sellerUseCase.validatePayment(paymentAmount, originalAmount);

        assertEquals(PaymentStatus.FULL, status);
    }

    @Test
    @DisplayName("Test validatePayment with excess amount")
    void testValidatePaymentExcess() {
        BigDecimal paymentAmount = BigDecimal.valueOf(150);
        BigDecimal originalAmount = BigDecimal.valueOf(100);

        PaymentStatus status = sellerUseCase.validatePayment(paymentAmount, originalAmount);

        assertEquals(PaymentStatus.EXCESS, status);
    }
}
