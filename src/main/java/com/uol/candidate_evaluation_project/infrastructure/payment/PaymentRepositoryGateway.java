package com.uol.candidate_evaluation_project.infrastructure.payment;

import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.main.error.Errors;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PaymentRepositoryGateway implements PaymentGateway {
    private final PaymentRepository paymentRepository;
    private final PaymentEntityMapper paymentEntityMapper;

    public PaymentRepositoryGateway(PaymentRepository paymentRepository, PaymentEntityMapper paymentEntityMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentEntityMapper = paymentEntityMapper;
    }

    @Transactional
    @Override
    public Payment create(Payment payment) {
        return save(paymentEntityMapper.toEntity(payment));
    }

    @Override
    public Payment findById(String billingCode) {
        PaymentEntity paymentEntity = paymentRepository.findById(billingCode)
                .orElseThrow(() -> new Errors.ResourceNotFoundException("Payment not found with code: " + billingCode));
        return paymentEntityMapper.toDomainObj(paymentEntity);
    }

    @Override
    public boolean existsById(String billingCode) {
        return paymentRepository.existsById(billingCode);
    }

    @Transactional
    @Override
    public Payment update(String billingCode, Payment payment) {
        PaymentEntity paymentEntity = paymentRepository.findById(billingCode)
                .orElseThrow(() -> new Errors.ResourceNotFoundException("Payment not found with code: " + billingCode));
        paymentEntity.update(payment);
        return save(paymentEntity);
    }

    @Transactional
    @Override
    public Payment updateStatus(Payment payment) {
        PaymentEntity paymentEntity = paymentRepository.findById(payment.billingCode())
                .orElseThrow(() -> new Errors.ResourceNotFoundException("Payment not found with code: " +
                        payment.billingCode()));
        paymentEntity.setStatus(payment.status());
        return save(paymentEntity);
    }

    @Transactional
    @Override
    public void delete(String billingCode) {
        PaymentEntity paymentEntity = paymentRepository.findById(billingCode)
                .orElseThrow(() -> new Errors.ResourceNotFoundException("Payment not found with code: " + billingCode));
        paymentRepository.delete(paymentEntity);
    }

    private Payment save(final PaymentEntity paymentEntity) {
        PaymentEntity payment = this.paymentRepository.save(paymentEntity);
        return paymentEntityMapper.toDomainObj(payment);
    }
}
