package com.webfluxpattern.section_03.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor (staticName = "create")
@NoArgsConstructor
public class Product {

    private int id;
    private String category;
    private String description;
    private double price;

}
