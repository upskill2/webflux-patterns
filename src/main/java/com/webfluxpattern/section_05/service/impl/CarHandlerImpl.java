package com.webfluxpattern.section_05.service.impl;

import com.webfluxpattern.section_05.client.CarClient;
import com.webfluxpattern.section_05.dto.request.CarReservationRequest;
import com.webfluxpattern.section_05.dto.request.ReservationItemRequest;
import com.webfluxpattern.section_05.dto.request.ReservationType;
import com.webfluxpattern.section_05.dto.response.CarReservationResponse;
import com.webfluxpattern.section_05.dto.response.ReservationItemResponse;
import com.webfluxpattern.section_05.service.ReservationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
@Service
public class CarHandlerImpl extends ReservationHandler {

    @Autowired
    private CarClient carClient;

    @Override
    protected ReservationType getType () {
        return ReservationType.CAR;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve (final Flux<ReservationItemRequest> fluxRequest) {
        return fluxRequest
                .map (this::toCarRequest)
                .transform (carClient::reserve)
                .map (this::toReservationItemResponse);

    }
    private CarReservationRequest toCarRequest(ReservationItemRequest reservationItemRequest) {
        return CarReservationRequest.builder()
                .city (reservationItemRequest.getCity())
                .category (reservationItemRequest.getCategory())
                .drop (reservationItemRequest.getTo ())
                .pickup (reservationItemRequest.getFrom ())
                .build();
    }

    private ReservationItemResponse toReservationItemResponse(CarReservationResponse carReservationResponse) {
        return ReservationItemResponse.builder()
                .city (carReservationResponse.getCity())
                .category (carReservationResponse.getCategory())
                .from (carReservationResponse.getPickup())
                .to (carReservationResponse.getDrop())
                .type (ReservationType.CAR)
                .itemId (carReservationResponse.getReservationId ())
                .price (carReservationResponse.getPrice ())
                .build();

    }
}
