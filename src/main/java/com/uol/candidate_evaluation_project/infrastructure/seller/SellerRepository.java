package com.uol.candidate_evaluation_project.infrastructure.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, String> {
}
