package com.demopipeline.payments.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * Event representing a state change for a payment as it flows through the pipeline.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String eventId;               // Unique event ID (UUID)
    private PaymentEventType eventType;   // CREATED, VALIDATED, ROUTED, etc.
    private String paymentId;             // ID from PaymentRequest
    private PaymentStatus status;         // PENDING, ACCEPTED, REJECTED, etc.
    private Instant timestamp;           // When the event occurred

    private String errorCode;            // Optional error code for failures
    private String errorMessage;         // Optional human-readable error
}
