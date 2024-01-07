package com.webfluxpattern.section_04.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
public class OrderRequest {
    private int userId;
    private int productId;
    private int quantity;
}
