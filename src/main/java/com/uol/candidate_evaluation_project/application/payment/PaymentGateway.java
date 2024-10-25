package com.uol.candidate_evaluation_project.application.payment;

import com.uol.candidate_evaluation_project.domain.payment.Payment;

public interface PaymentGateway {
    Payment create(Payment payment);

    Payment findById(String billingCode);

    boolean existsById(String billingCode);

    Payment update(String billingCode, Payment payment);

    Payment updateStatus(Payment payment);

    void delete(String billingCode);
}
