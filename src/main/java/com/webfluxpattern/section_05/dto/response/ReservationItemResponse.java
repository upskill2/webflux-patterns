package com.webfluxpattern.section_05.dto.response;

import com.webfluxpattern.section_05.dto.request.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationItemResponse {

    private ReservationType type;
    private String city;
    private String category;
    private LocalDate from;
    private LocalDate to;
    private UUID itemId;
    private double price;


}
