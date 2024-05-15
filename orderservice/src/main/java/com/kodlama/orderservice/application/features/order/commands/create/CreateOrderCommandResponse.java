package com.kodlama.orderservice.application.features.order.commands.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderCommandResponse {
    private Integer orderId;
}
