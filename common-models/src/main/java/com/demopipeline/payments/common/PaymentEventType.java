package com.demopipeline.payments.common;

public enum PaymentEventType {
    CREATED,      // Payment created by client/API
    VALIDATED,    // Payment validated by validator service
    ROUTED,       // Routing decision made
    BOOKED,       // Ledger posting completed
    FAILED        // Processing failed at some stage
}
