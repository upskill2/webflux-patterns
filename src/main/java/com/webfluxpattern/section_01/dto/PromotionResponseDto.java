package com.webfluxpattern.section_01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionResponseDto {

    private int id;
    private String type;
    private double discount;
    private LocalDate endDate;

}
