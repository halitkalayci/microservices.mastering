package com.kodlama.orderservice.controller;

import an.awesome.pipelinr.Pipeline;
import com.kodlama.orderservice.application.features.order.commands.create.CreateOrderCommand;
import com.kodlama.orderservice.application.features.order.commands.create.CreateOrderCommandResponse;
import com.kodlama.orderservice.client.ProductClient;
import com.kodlama.orderservice.constant.GlobalConstants;
import com.kodlama.orderservice.exception.BusinessException;
import com.kodlama.orderservice.service.OrderService;
import com.kodlamaio.common.events.OrderCreatedEvent;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController
{
    private final Pipeline pipeline;
    private final ProductClient productClient;
    private final OrderService orderService;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;


    @PostMapping()
    public ResponseEntity<CreateOrderCommandResponse> add(@RequestBody CreateOrderCommand command)
    {
        CreateOrderCommandResponse response = command.execute(pipeline);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(1L, new Date());
        kafkaTemplate.sendDefault(orderCreatedEvent);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String hello(@RequestParam Boolean shouldBreak)  {
        if(shouldBreak)
            throw new BusinessException("Deneme");
        String productResponse = orderService.get();
        return "Hello" + productResponse;
    }

    //@CircuitBreaker(name="circuit1",fallbackMethod="fallbackMethod")
    private String getProductResponse()
    {
        throw new RuntimeException("");
        //String response = productClient.get();
        //return "";
    }


}
