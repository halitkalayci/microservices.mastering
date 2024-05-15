package com.kodlama.orderservice.application.features.order.commands.create;

import an.awesome.pipelinr.Command;
import com.kodlama.orderservice.core.application.pipelines.auth.AuthCommand;
import com.kodlama.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderCommand implements Command<CreateOrderCommandResponse>
{
    private String customerId;
    private double totalPrice;
    private Date shipDate;

    @RequiredArgsConstructor
    @Component
    public static class CreateOrderCommandHandler implements Command.Handler<CreateOrderCommand, CreateOrderCommandResponse>
    {
        // private final OrderRepository orderRepository;

        @Override
        public CreateOrderCommandResponse handle(CreateOrderCommand createOrderCommand) {
            //orderRepository.save();
            return null;
        }
    }
}
