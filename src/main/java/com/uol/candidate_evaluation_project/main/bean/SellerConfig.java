package com.uol.candidate_evaluation_project.main.bean;

import com.uol.candidate_evaluation_project.application.payment.PaymentGateway;
import com.uol.candidate_evaluation_project.application.seller.SellerGateway;
import com.uol.candidate_evaluation_project.application.seller.SellerUseCase;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntityMapper;
import com.uol.candidate_evaluation_project.infrastructure.payment.payload.PaymentDTOMapper;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerEntityMapper;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerRepository;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerRepositoryGateway;
import com.uol.candidate_evaluation_project.infrastructure.seller.payload.SellerDTOMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SellerConfig {
    @Bean
    SellerUseCase createSellerUseCase(SellerGateway sellerGateway, PaymentGateway paymentGateway,
                                      ApplicationEventPublisher applicationEventPublisher,
                                      PaymentEntityMapper paymentEntityMapper, SellerEntityMapper sellerEntityMapper) {
        return new SellerUseCase(sellerGateway, paymentGateway, applicationEventPublisher, paymentEntityMapper,
                sellerEntityMapper);
    }

    @Bean
    SellerGateway sellerGateway(SellerRepository sellerRepository, SellerEntityMapper sellerEntityMapper) {
        return new SellerRepositoryGateway(sellerRepository, sellerEntityMapper
        );
    }

    @Bean
    SellerEntityMapper sellerEntityMapper(PaymentEntityMapper paymentEntityMapper) {
        return new SellerEntityMapper(paymentEntityMapper);
    }

    @Bean
    SellerDTOMapper sellerDTOMapper(PaymentDTOMapper paymentDTOMapper) {
        return new SellerDTOMapper(paymentDTOMapper);
    }
}
