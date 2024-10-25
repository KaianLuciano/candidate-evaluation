package com.uol.candidate_evaluation_project.infrastructure.payment.payload;


import com.uol.candidate_evaluation_project.domain.payment.Payment;

public class PaymentDTOMapper {
    public CreatePaymentResponse toCreateResponse(Payment payment) {
        return new CreatePaymentResponse(payment.billingCode(), payment.value());
    }

    public UpdatePaymentResponse toUpdateResponse(Payment payment) {
        return new UpdatePaymentResponse(payment.billingCode(), payment.value(), payment.status());
    }

    public PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(payment.billingCode(), payment.value(), payment.status());
    }

    public Payment toPayment(CreatePaymentRequest createPaymentRequest) {
        return new Payment(createPaymentRequest.billingCode(), createPaymentRequest.value(),
                null);
    }

    public Payment toPayment(UpdatePaymentRequest updatePaymentRequest) {
        return new Payment(updatePaymentRequest.billingCode(), updatePaymentRequest.value(),
                updatePaymentRequest.paymentStatus());
    }
}
