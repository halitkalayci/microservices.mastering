package com.kodlama.productservice.consumer;

import com.kodlamaio.common.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @KafkaListener(topics = "orderservice", groupId = "productService")
    public void onOrderCreated(OrderCreatedEvent event)
    {
        System.out.println("New order created with id:" + event.getId());
    }
}
