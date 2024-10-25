package com.uol.candidate_evaluation_project.application.payment;


import com.uol.candidate_evaluation_project.domain.payment.Payment;

public class PaymentUseCase {
    private final PaymentGateway paymentGateway;

    public PaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Payment create(Payment payment) {
        return paymentGateway.create(payment);
    }

    public Payment findById(String billingCode) {
        return paymentGateway.findById(billingCode);
    }

    public Payment update(String billingCode, Payment payment) {
        return paymentGateway.update(billingCode, payment);
    }

    public void delete(String billingCode) {
        paymentGateway.delete(billingCode);
    }
}
