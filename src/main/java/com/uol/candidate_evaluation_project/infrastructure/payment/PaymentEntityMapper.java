package com.uol.candidate_evaluation_project.infrastructure.payment;


import com.uol.candidate_evaluation_project.domain.payment.Payment;

public class PaymentEntityMapper {
    public PaymentEntity toEntity(Payment paymentDomainObj) {
        return new PaymentEntity(paymentDomainObj.billingCode(), paymentDomainObj.value(), paymentDomainObj.status());
    }

    public Payment toDomainObj(PaymentEntity paymentEntity) {
        return new Payment(paymentEntity.getBillingCode(), paymentEntity.getValue(), paymentEntity.getStatus());
    }
}
