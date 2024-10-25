package com.uol.candidate_evaluation_project.application.seller;

import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.main.config.event.payment.PaymentValidationEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;

public class SellerUseCase {
    private final SellerGateway sellerGateway;
    private final PaymentGateway paymentGateway;
    private final ApplicationEventPublisher eventPublisher;

    public SellerUseCase(SellerGateway sellerGateway, PaymentGateway paymentGateway,
                         ApplicationEventPublisher eventPublisher) {
        this.sellerGateway = sellerGateway;
        this.paymentGateway = paymentGateway;
        this.eventPublisher = eventPublisher;
    }

    public Seller create(Seller seller) {
        return sellerGateway.create(seller);
    }

    public Seller findById(String sellerCode) {
        return sellerGateway.findById(sellerCode);
    }

    public Seller update(String sellerCode, Seller seller) {
        Seller sellerGatewayById = sellerGateway.findById(sellerCode);
        return sellerGateway.update(sellerGatewayById);
    }

    public void updateStatus(String sellerCode, Seller seller) {
        if (sellerGateway.existsById(sellerCode) &&
                seller.payments().stream()
                        .allMatch(lambdaEntity -> paymentGateway.existsById(lambdaEntity.billingCode()))) {

            seller.payments().forEach(lambdaEntity -> {
                Payment payment = paymentGateway.findById(lambdaEntity.billingCode());
                PaymentStatus status = validatePayment(lambdaEntity.value(), payment.value());
                eventPublisher.publishEvent(new PaymentValidationEvent(payment, status));
            });
        }
    }

    public void delete(String sellerCode) {
        sellerGateway.delete(sellerCode);
    }

    public PaymentStatus validatePayment(BigDecimal paymentAmount, BigDecimal originalAmount) {
        if (paymentAmount.compareTo(originalAmount) < 0) {
            return PaymentStatus.PARTIAL;
        } else if (paymentAmount.compareTo(originalAmount) == 0) {
            return PaymentStatus.FULL;
        } else {
            return PaymentStatus.EXCESS;
        }
    }

}
