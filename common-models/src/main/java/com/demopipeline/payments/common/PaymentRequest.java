package com.demopipeline.payments.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents an incoming payment initiation request from a client/channel.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotBlank
    private String id;                // UUID string for payment reference

    @NotBlank
    private String debtorAccount;     // Source account

    @NotBlank
    private String creditorAccount;   // Destination account

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String currency;          // e.g. "EUR", "INR", "USD"

    @NotBlank
    private String channel;           // e.g. "MOBILE", "BRANCH", "API"

    @NotBlank
    private String country;           // ISO country code, e.g. "DE", "IN"

    private String schemeHint;        // Optional hint for scheme selection (e.g. "SEPA")
}
