package com.demopipeline.payments.initiator.controller;

import com.demopipeline.payments.common.PaymentRequest;
import com.demopipeline.payments.initiator.entity.PaymentEntity;
import com.demopipeline.payments.initiator.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
//@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentRequest> createPayment(@RequestBody PaymentRequest request) {
        PaymentRequest created = paymentService.createPayment(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentEntity> getPayment(@PathVariable String id) {
        PaymentEntity entity = paymentService.getPayment(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @GetMapping("/health")
    public String health() {
        return "Payment Initiator LIVE!";
    }
}
