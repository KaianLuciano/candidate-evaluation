package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.application.seller.SellerGateway;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntity;
import com.uol.candidate_evaluation_project.main.error.Errors;

import java.util.List;

public class SellerRepositoryGateway implements SellerGateway {
    private final SellerRepository sellerRepository;
    private final SellerEntityMapper sellerEntityMapper;

    public SellerRepositoryGateway(SellerRepository sellerRepository, SellerEntityMapper sellerEntityMapper) {
        this.sellerRepository = sellerRepository;
        this.sellerEntityMapper = sellerEntityMapper;
    }

    @Override
    public Seller create(SellerEntity sellerEntity, List<PaymentEntity> payments) {
        sellerEntity.getPayments().addAll(payments);
        return save(sellerEntity);
    }

    @Override
    public Seller findById(String sellerCode) {
        SellerEntity sellerEntity = sellerRepository.findById(sellerCode)
                .orElseThrow(() -> new Errors.ResourceNotFoundException("Seller not found with code: " + sellerCode));
        return sellerEntityMapper.toDomainObj(sellerEntity);
    }

    @Override
    public boolean existsById(String sellerCode) {
        return sellerRepository.existsById(sellerCode);
    }

    @Override
    public Seller update(Seller seller) {
        return save(sellerEntityMapper.toEntity(seller));
    }

    @Override
    public void delete(String sellerCode) {
        SellerEntity sellerEntity = sellerRepository.findById(sellerCode)
                .orElseThrow(() -> new Errors.ResourceNotFoundException("Seller not found with code: " + sellerCode));
        sellerRepository.delete(sellerEntity);
    }

    private Seller save(final SellerEntity sellerEntity) {
        SellerEntity seller = this.sellerRepository.save(sellerEntity);
        return sellerEntityMapper.toDomainObj(seller);
    }
}
