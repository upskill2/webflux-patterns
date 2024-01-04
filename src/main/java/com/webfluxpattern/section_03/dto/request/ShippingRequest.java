package com.webfluxpattern.section_03.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
public class ShippingRequest {

    private int quantity;
    private UUID orderId;
    private int userId;
}
