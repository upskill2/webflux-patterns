package com.webfluxpattern.section_02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor (staticName = "create")
@NoArgsConstructor
@Builder
public class FlightResult {

    private String airline;
    private String from;
    private String to;
    private double price;
    private LocalDate date;

}
