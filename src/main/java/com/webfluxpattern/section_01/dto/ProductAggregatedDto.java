package com.webfluxpattern.section_01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor (staticName = "create")
public class ProductAggregatedDto {

    private int id;
    private String category;
    private String description;
    private Price price;
    private List<ReviewResponseDto> reviews;

}
