package com.webfluxpattern.section_05.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarReservationRequest {

    private String city;
    private LocalDate pickup;
    private LocalDate drop;
    private String category;


}
