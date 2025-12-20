package com.demopipeline.payments.common;

public enum PaymentStatus {
    PENDING,      // Created but not yet validated
    ACCEPTED,     // Validated and accepted for further processing
    REJECTED,     // Failed validation or business rules
    REPAIR,       // Needs manual or automated repair
    BOOKED,       // Successfully posted to ledger
    FAILED        // Failed during routing/booking
}
