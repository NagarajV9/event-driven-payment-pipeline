package com.demopipeline.payments.initiator.service;

import com.demopipeline.payments.common.PaymentEvent;
import com.demopipeline.payments.common.PaymentEventType;
import com.demopipeline.payments.common.PaymentRequest;
import com.demopipeline.payments.common.PaymentStatus;
import com.demopipeline.payments.initiator.entity.PaymentEntity;
import com.demopipeline.payments.initiator.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final String INCOMING_QUEUE = "q.payments.incoming";

    private final PaymentRepository paymentRepository;
    private final JmsTemplate jmsTemplate;


    public PaymentRequest createPayment(PaymentRequest request) {
        // Ensure ID
        String id = request.getId() != null ? request.getId() : UUID.randomUUID().toString();
        request.setId(id);

        // Persist entity
        PaymentEntity entity = new PaymentEntity();
        entity.setId(id);
        entity.setDebtorAccount(request.getDebtorAccount());
        entity.setCreditorAccount(request.getCreditorAccount());
        entity.setAmount(request.getAmount());
        entity.setCurrency(request.getCurrency());
        entity.setChannel(request.getChannel());
        entity.setCountry(request.getCountry());
        entity.setSchemeHint(request.getSchemeHint());
        paymentRepository.save(entity);

        // Build event
        PaymentEvent event = PaymentEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType(PaymentEventType.CREATED)
                .paymentId(id)
                .status(PaymentStatus.PENDING)
                .timestamp(Instant.now())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // ← FIX Instant
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  // ISO-8601

        // Publish to ActiveMQ
        try {
            String jsonEvent = objectMapper.writeValueAsString(event);
            jmsTemplate.convertAndSend(INCOMING_QUEUE, jsonEvent);
            System.out.println("✅ ActiveMQ: " + INCOMING_QUEUE + " → " + jsonEvent.substring(0, 100) + "...");
        } catch (Exception e) {
            System.err.println("❌ ActiveMQ failed (non-critical): " + e.getMessage());
        }

        return request;
    }

    public PaymentEntity getPayment(String id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
