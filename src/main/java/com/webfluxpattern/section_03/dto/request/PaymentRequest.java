package com.webfluxpattern.section_03.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
public class PaymentRequest {
    private int userId;
    private int amount;
    private UUID orderId;
}
