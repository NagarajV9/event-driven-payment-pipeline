package com.demopipeline.payments.initiator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    private String id;

    private String debtorAccount;
    private String creditorAccount;

    private BigDecimal amount;
    private String currency;
    private String channel;
    private String country;
    private String schemeHint;
}
