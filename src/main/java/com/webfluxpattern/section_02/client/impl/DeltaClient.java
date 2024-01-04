package com.webfluxpattern.section_02.client.impl;

import com.webfluxpattern.section_02.client.FlightClient;
import com.webfluxpattern.section_02.dto.FlightResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class DeltaClient implements FlightClient {

    private final WebClient webClient;

    public DeltaClient (@Value ("${sec02.delta.service}") String baseUrl) {
        this.webClient = WebClient.builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    @Override
    public Flux<FlightResult> getFlightResults (String from, String to) {
        return webClient
                .get ()
                .uri ("/{from}/{to}", from, to)
                .retrieve ()
                .bodyToFlux (FlightResult.class)
                .onErrorResume (error -> Mono.empty ());


    }

}
