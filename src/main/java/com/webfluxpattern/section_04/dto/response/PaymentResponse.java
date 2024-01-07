package com.webfluxpattern.section_04.dto.response;

import com.webfluxpattern.section_04.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
@Builder
public class PaymentResponse {

    private int userId;
    private String name;
    private double balance;
    private Status status;
}
