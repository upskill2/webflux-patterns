package com.webfluxpattern.section_01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private int initialPrice;
    private double discount;
    private double finalPrice;
    private double savings;
    private LocalDate endDate;


}
