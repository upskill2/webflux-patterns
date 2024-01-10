package com.webfluxpattern.section_05.service;

import com.webfluxpattern.section_05.dto.request.ReservationItemRequest;
import com.webfluxpattern.section_05.dto.request.ReservationType;
import com.webfluxpattern.section_05.dto.response.ReservationItemResponse;
import com.webfluxpattern.section_05.dto.response.ReservationResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;

@Service
public class ReservationService {

    private final Map<ReservationType, ReservationHandler> reservationHandlerMap;

    public ReservationService (List<ReservationHandler> handlers) {
        this.reservationHandlerMap = handlers
                .stream ()
                .collect (
                        Collectors.toMap (ReservationHandler::getType, identity ()));
    }

    public Mono<ReservationResponse> reserve (Flux<ReservationItemRequest> reservationItemRequest) {
        return reservationItemRequest
                .groupBy (ReservationItemRequest::getType)
                .flatMap (this::aggregator)
                .collectList ()
                .map (this::toReservationResponse);
    }

    private Flux<ReservationItemResponse> aggregator (GroupedFlux<ReservationType, ReservationItemRequest> groupedRequests) {
        return reservationHandlerMap.get (groupedRequests.key ())
                .reserve (groupedRequests);
    }

    private ReservationResponse toReservationResponse (List<ReservationItemResponse> reservationItemResponses) {
        return ReservationResponse.builder ()
                .reservations (reservationItemResponses)
                .reservationId (reservationItemResponses.stream ()
                        .map (ReservationItemResponse::getItemId).reduce ((a, b) -> b).orElse (UUID.randomUUID ())
                )
                .price (reservationItemResponses.stream ()
                        .mapToDouble (ReservationItemResponse::getPrice)
                        .sum ())
                .build ();
    }

}
