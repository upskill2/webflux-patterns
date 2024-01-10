package com.webfluxpattern.section_05.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomReservationResponse {

    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String category;
    private UUID reservationId;
    private double price;
}
