package com.kodlamaio.common.events;

import com.kodlamaio.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseEvent {
    private PaymentStatus paymentStatus;
}
