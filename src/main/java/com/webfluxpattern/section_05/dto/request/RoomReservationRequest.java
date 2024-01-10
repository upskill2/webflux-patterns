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
public class RoomReservationRequest {

    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String category;
}
