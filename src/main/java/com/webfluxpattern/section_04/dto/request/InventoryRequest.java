package com.webfluxpattern.section_04.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
public class InventoryRequest {

    private UUID orderId;
    private int productId;
    private int quantity;
}
