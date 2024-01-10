package com.webfluxpattern.section_05.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationItemRequest {

    private ReservationType type;
    private String city;
    private String category;
    private LocalDate from;
    private LocalDate to;



}
