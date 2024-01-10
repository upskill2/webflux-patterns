package com.webfluxpattern.section_05.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {

    private UUID reservationId;
    private double price;
    private List<ReservationItemResponse> reservations;
}
