package com.webfluxpattern.section_04.dto.response;

import com.webfluxpattern.section_04.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
@Builder
public class PaymentResponse {

    private UUID paymentId;
    private int userId;
    private String name;
    private int balance;
    private Status status;

    public void setStatus (Status status) {
        this.status = status;
    }
}
