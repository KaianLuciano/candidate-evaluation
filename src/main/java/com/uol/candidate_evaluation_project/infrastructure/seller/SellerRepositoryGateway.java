package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.application.seller.SellerGateway;
import com.uol.candidate_evaluation_project.domain.seller.Seller;
import com.uol.candidate_evaluation_project.main.error.Errors;

public class SellerRepositoryGateway implements SellerGateway {
    private final SellerRepository sellerRepository;
    private final SellerEntityMapper sellerEntityMapper;

    public SellerRepositoryGateway(SellerRepository sellerRepository, SellerEntityMapper sellerEntityMapper) {
        this.sellerRepository = sellerRepository;
        this.sellerEntityMapper = sellerEntityMapper;
    }

    @Override
    public Seller create(Seller seller) {
        return save(sellerEntityMapper.toEntity(seller));
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
        SellerEntity payment = this.sellerRepository.save(sellerEntity);
        return sellerEntityMapper.toDomainObj(payment);
    }
}
