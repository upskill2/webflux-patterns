package com.webfluxpattern.section_10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor (staticName = "create")
public class ProductAggregatedDto {

    private int id;
    private String category;
    private String description;
    private List<ReviewResponseDto> reviews;

}
