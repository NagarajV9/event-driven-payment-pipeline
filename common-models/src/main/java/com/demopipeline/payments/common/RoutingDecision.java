package com.demopipeline.payments.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the routing decision taken for a payment (which scheme it goes to).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutingDecision {

    private String paymentId;     // ID from PaymentRequest
    private Scheme targetScheme;  // SEPA, SWIFT, DOMESTIC
    private String reason;        // Why this scheme was chosen (e.g. "EUR + SEPA country")
}
