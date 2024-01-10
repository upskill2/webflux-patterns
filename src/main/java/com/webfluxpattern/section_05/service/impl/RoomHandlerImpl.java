package com.webfluxpattern.section_05.service.impl;

import com.webfluxpattern.section_05.client.RoomClient;
import com.webfluxpattern.section_05.dto.request.ReservationItemRequest;
import com.webfluxpattern.section_05.dto.request.ReservationType;
import com.webfluxpattern.section_05.dto.request.RoomReservationRequest;
import com.webfluxpattern.section_05.dto.response.ReservationItemResponse;
import com.webfluxpattern.section_05.dto.response.RoomReservationResponse;
import com.webfluxpattern.section_05.service.ReservationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RoomHandlerImpl extends ReservationHandler {

    @Autowired
    private RoomClient roomClient;

    @Override
    protected ReservationType getType () {
        return ReservationType.ROOM;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve (final Flux<ReservationItemRequest> fluxRequest) {
        return fluxRequest.map (this::toRoomRequest)
                .transform (roomClient::reserve)
                .map (this::toReservationItemResponse);
    }

    private RoomReservationRequest toRoomRequest (ReservationItemRequest reservationItemRequest) {
        return RoomReservationRequest.builder ()
                .city (reservationItemRequest.getCity ())
                .category (reservationItemRequest.getCategory ())
                .checkIn (reservationItemRequest.getFrom ())
                .checkOut (reservationItemRequest.getTo ())
                .build ();
    }
    private ReservationItemResponse toReservationItemResponse (RoomReservationResponse roomReservationResponse) {
        return ReservationItemResponse.builder ()
                .city (roomReservationResponse.getCity ())
                .category (roomReservationResponse.getCategory ())
                .from (roomReservationResponse.getCheckIn ())
                .to (roomReservationResponse.getCheckOut ())
                .type (ReservationType.ROOM)
                .itemId (roomReservationResponse.getReservationId ())
                .price (roomReservationResponse.getPrice ())
                .build ();
    }

}
