package com.uol.candidate_evaluation_project.infrastructure.payment;


import com.uol.candidate_evaluation_project.domain.payment.Payment;
import com.uol.candidate_evaluation_project.domain.payment.PaymentStatus;
import com.uol.candidate_evaluation_project.infrastructure.seller.SellerEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class PaymentEntity {
    @Id
    @Column(unique = true, nullable = false)
    private String billingCode;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerEntity seller;


    public PaymentEntity() {
    }

    public PaymentEntity(String billingCode, BigDecimal value, PaymentStatus status) {
        this.billingCode = billingCode;
        this.value = value;
        this.status = status;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public SellerEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerEntity seller) {
        this.seller = seller;
    }

    public void update(Payment payment) {
        this.billingCode = payment.billingCode();
        this.value = payment.value();
        this.status = payment.status();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(billingCode, that.billingCode) && Objects.equals(value, that.value) && status == that.status && Objects.equals(seller, that.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billingCode, value, status, seller);
    }
}
