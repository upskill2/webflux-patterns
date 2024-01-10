package com.webfluxpattern.section_05.controller;

import com.webfluxpattern.section_05.dto.request.ReservationItemRequest;
import com.webfluxpattern.section_05.dto.response.ReservationResponse;
import com.webfluxpattern.section_05.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sec05")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservation")
    public Mono<ReservationResponse> reserve(@RequestBody Flux<ReservationItemRequest> reservationItemRequest){
        return reservationService.reserve(reservationItemRequest);

    }

}
