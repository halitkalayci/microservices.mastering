package com.kodlamaio.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentRequiredEvent {
    private Long orderId;
    private Double amount;
}
