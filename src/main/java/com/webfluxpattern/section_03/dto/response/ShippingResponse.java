package com.webfluxpattern.section_03.dto.response;

import com.webfluxpattern.section_03.dto.Status;
import com.webfluxpattern.section_03.dto.request.ShippingAddress;
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
    private LocalDate deliveryDate;
    private ShippingAddress address;
}
