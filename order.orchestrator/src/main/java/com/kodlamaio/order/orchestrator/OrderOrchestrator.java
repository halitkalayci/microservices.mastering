package com.kodlamaio.order.orchestrator;

import com.kodlamaio.common.enums.PaymentStatus;
import com.kodlamaio.common.events.OrderCreatedEvent;
import com.kodlamaio.common.events.PaymentRequiredEvent;
import com.kodlamaio.common.events.PaymentResponseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class OrderOrchestrator
{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "orderCreated", groupId = "order-orchestrator")
    public void orderCreatedEvent(OrderCreatedEvent event)
    {
        System.out.println("Order Created Event");
        PaymentRequiredEvent paymentEvent = new PaymentRequiredEvent(event.getId(), new Random().nextDouble());
        kafkaTemplate.send("paymentRequired", paymentEvent);
    }

    @KafkaListener(topics= "paymentResponse", groupId = "order-orchestrator")
    public void paymentResponse(PaymentResponseEvent event)
    {
        System.out.println("Payment Response");
        if(event.getPaymentStatus().equals(PaymentStatus.SUCCESS))
            kafkaTemplate.send("inventoryDecrease", null);
        else
            kafkaTemplate.send("paymentFailed", null);
    }

    @KafkaListener(topics="inventoryResponse", groupId = "order-orchestrator")
    public void inventoryResponse()
    {

    }
}
