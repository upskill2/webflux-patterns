package com.webfluxpattern.section_04.dto.response;

import com.webfluxpattern.section_04.dto.Status;
import com.webfluxpattern.section_04.dto.request.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
@Builder
public class ShippingResponse {

    private UUID orderId;
    private int quantity;
    private Status status;
    private LocalDate expectedDelivery;
    private ShippingAddress address;
}
