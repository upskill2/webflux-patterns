package com.webfluxpattern.section_05.client;

import com.webfluxpattern.section_05.dto.request.CarReservationRequest;
import com.webfluxpattern.section_05.dto.response.CarReservationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CarClient {

    private final WebClient webClient;

    public CarClient (@Value ("${sec05.car.service}") String carServiceUrl) {
        this.webClient = WebClient
                .builder ()
                .baseUrl (carServiceUrl)
                .build ();
    }

    public Flux<CarReservationResponse> reserve (Flux<CarReservationRequest> request) {
        return webClient
                .post ()
                .body (request, CarReservationRequest.class)
                .retrieve ()
                .bodyToFlux (CarReservationResponse.class)
                .onErrorResume (throwable -> Flux.empty ());

    }

}
