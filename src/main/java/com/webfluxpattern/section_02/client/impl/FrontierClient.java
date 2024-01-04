package com.webfluxpattern.section_02.client.impl;

import com.webfluxpattern.section_02.client.FlightClient;
import com.webfluxpattern.section_02.dto.FlightResult;
import com.webfluxpattern.section_02.dto.FrontierRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FrontierClient implements FlightClient {

    private final WebClient webClient;

    public FrontierClient (@Value ("${sec02.frontier.service}") String baseUrl) {
        this.webClient = WebClient
                .builder ()
                .baseUrl (baseUrl)
                .build ();
    }


    @Override
    public Flux<FlightResult> getFlightResults (final String from, final String to) {
        return webClient
                .post ()
                .bodyValue (FrontierRequest.create (from, to))
                .retrieve ()
                .bodyToFlux (FlightResult.class)
                .onErrorResume (error -> Mono.empty ());

    }


}
