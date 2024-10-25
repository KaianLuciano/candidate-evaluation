package com.uol.candidate_evaluation_project.main.bean;

import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.application.payment.PaymentUseCase;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntityMapper;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentRepository;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentRepositoryGateway;
import com.uol.candidate_evaluation_project.infrastructure.payment.payload.PaymentDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Bean
    PaymentUseCase createPaymentUseCase(PaymentGateway paymentGateway) {
        return new PaymentUseCase(paymentGateway);
    }

    @Bean
    PaymentGateway paymentGateway(PaymentRepository paymentRepository, PaymentEntityMapper paymentEntityMapper) {
        return new PaymentRepositoryGateway(paymentRepository, paymentEntityMapper);
    }

    @Bean
    PaymentEntityMapper paymentEntityMapper() {
        return new PaymentEntityMapper();
    }

    @Bean
    PaymentDTOMapper paymentDTOMapper() {
        return new PaymentDTOMapper();
    }
}
