package com.uol.candidate_evaluation_project.application.seller;

import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntityMapper;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerEntityMapper;
import com.uol.candidate_evaluation_project.main.config.event.payment.PaymentValidationEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.List;

public class SellerUseCase {
    private final SellerGateway sellerGateway;
    private final PaymentGateway paymentGateway;
    private final PaymentEntityMapper paymentEntityMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final SellerEntityMapper sellerEntityMapper;

    public SellerUseCase(SellerGateway sellerGateway, PaymentGateway paymentGateway,
                         ApplicationEventPublisher eventPublisher, PaymentEntityMapper paymentEntityMapper,
                         SellerEntityMapper sellerEntityMapper) {
        this.sellerGateway = sellerGateway;
        this.paymentGateway = paymentGateway;
        this.eventPublisher = eventPublisher;
        this.paymentEntityMapper = paymentEntityMapper;
        this.sellerEntityMapper = sellerEntityMapper;
    }

    public Seller create(Seller seller, List<String> paymentsCodes) {
        List<Payment> payments = paymentsCodes.stream().map(paymentGateway::findById).toList();
        return sellerGateway.create(sellerEntityMapper.toEntity(seller), payments.stream().
                map(paymentEntityMapper::toEntity).toList());
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
