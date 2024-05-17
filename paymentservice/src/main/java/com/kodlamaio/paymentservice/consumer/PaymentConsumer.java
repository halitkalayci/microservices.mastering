package com.kodlamaio.paymentservice.consumer;

import com.kodlamaio.common.enums.PaymentStatus;
import com.kodlamaio.common.events.PaymentRequiredEvent;
import com.kodlamaio.common.events.PaymentResponseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {
    private final KafkaTemplate<String,Object> kafkaTemplate;

    @KafkaListener(topics = "paymentRequired", groupId = "paymentService")
    public void paymentRequired(PaymentRequiredEvent event)
    {
        // ... Simulation
        System.out.println("Payment event..");
        kafkaTemplate.send("paymentResponse", new PaymentResponseEvent(PaymentStatus.SUCCESS));
    }
}
