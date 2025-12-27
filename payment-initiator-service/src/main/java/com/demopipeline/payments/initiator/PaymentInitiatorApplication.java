package com.demopipeline.payments.initiator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.demopipeline.payments.initiator.controller",
        "com.demopipeline.payments.initiator.service"
})
public class PaymentInitiatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentInitiatorApplication.class, args);
    }
}
