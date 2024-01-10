package com.webfluxpattern.section_05.client;

import com.webfluxpattern.section_05.dto.request.CarReservationRequest;
import com.webfluxpattern.section_05.dto.request.RoomReservationRequest;
import com.webfluxpattern.section_05.dto.response.CarReservationResponse;
import com.webfluxpattern.section_05.dto.response.RoomReservationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class RoomClient {
    private final WebClient webClient;

    public RoomClient (@Value ("${sec05.room.service}") String carServiceUrl) {
        this.webClient = WebClient
                .builder ()
                .baseUrl (carServiceUrl)
                .build ();
    }

    public Flux<RoomReservationResponse> reserve (Flux<RoomReservationRequest> request) {
        return webClient
                .post ()
                .body (request, RoomReservationRequest.class)
                .retrieve ()
                .bodyToFlux (RoomReservationResponse.class)
                .onErrorResume (throwable -> Flux.empty ());

    }

}
