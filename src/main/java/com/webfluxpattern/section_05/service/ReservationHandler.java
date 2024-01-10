package com.webfluxpattern.section_05.service;

import com.webfluxpattern.section_05.dto.request.ReservationItemRequest;
import com.webfluxpattern.section_05.dto.request.ReservationType;
import com.webfluxpattern.section_05.dto.response.ReservationItemResponse;
import reactor.core.publisher.Flux;

public abstract class ReservationHandler {

    protected abstract ReservationType getType ();
    protected abstract Flux<ReservationItemResponse> reserve (Flux<ReservationItemRequest> fluxRequest);


}
