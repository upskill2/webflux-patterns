package com.webfluxpattern.section_04.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
public class PaymentRequest {
    private int amount;
    private UUID orderId;
    private int userId;
}
