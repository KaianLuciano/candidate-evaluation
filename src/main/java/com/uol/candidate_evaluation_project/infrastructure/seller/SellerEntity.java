package com.uol.candidate_evaluation_project.infrastructure.seller;

import com.uol.candidate_evaluation_project.infrastructure.payment.PaymentEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SellerEntity {

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PaymentEntity> payments = new ArrayList<>();
    @Id
    @Column(unique = true, nullable = false)
    private String sellerCode;

    public SellerEntity() {
    }

    public SellerEntity(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getCode() {
        return sellerCode;
    }

    public void setCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerEntity that = (SellerEntity) o;
        return Objects.equals(payments, that.payments) && Objects.equals(sellerCode, that.sellerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payments, sellerCode);
    }
}
