package com.webfluxpattern.section_04.dto;

import com.webfluxpattern.section_04.dto.request.InventoryRequest;
import com.webfluxpattern.section_04.dto.request.OrderRequest;
import com.webfluxpattern.section_04.dto.request.PaymentRequest;
import com.webfluxpattern.section_04.dto.request.ShippingRequest;
import com.webfluxpattern.section_04.dto.response.InventoryResponse;
import com.webfluxpattern.section_04.dto.response.PaymentResponse;
import com.webfluxpattern.section_04.dto.response.ShippingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrchestrationRequestContext {

    private final UUID orderId = UUID.randomUUID ();

    public OrchestrationRequestContext (OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    private OrderRequest orderRequest;
    private int productPrice;
    private PaymentRequest paymentRequest;
    private PaymentResponse paymentResponse;
    private InventoryRequest inventoryRequest;
    private InventoryResponse inventoryResponse;
    private ShippingRequest shippingRequest;
    private ShippingResponse shippingResponse;
    private Status status;
}
