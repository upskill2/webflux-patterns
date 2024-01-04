package com.webfluxpattern.section_03.dto.response;

import com.webfluxpattern.section_03.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
@Builder
public class InventoryResponse {

    private int productId;
    private int quantity;
    private int remainingQuantity;
    private Status status;
}
