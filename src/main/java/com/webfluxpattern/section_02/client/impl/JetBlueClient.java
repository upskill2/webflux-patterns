package com.webfluxpattern.section_02.client.impl;

import com.webfluxpattern.section_02.client.FlightClient;
import com.webfluxpattern.section_02.dto.FlightResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class JetBlueClient implements FlightClient {

    private final WebClient webClient;
    private static final String JET_BLUE = "JetBlue";

    public JetBlueClient (@Value ("${sec02.jetblue.service}") String baseUrl) {
        this.webClient = WebClient
                .builder ()
                .baseUrl (baseUrl)
                .build ();

    }

    @Override
    public Flux<FlightResult> getFlightResults (final String from, final String to) {
       return webClient
                .get ()
                .uri ("/{from}/{to}", from, to)
                .retrieve ()
                .bodyToFlux (FlightResult.class)
                .map (flightResult -> toFlightResult (flightResult, from, to))
                .onErrorResume (error -> Flux.empty ());

    }

    private FlightResult toFlightResult (final FlightResult flightResult, String from, String to) {
        flightResult.setTo (to);
        flightResult.setFrom (from);
        flightResult.setAirline (JET_BLUE);
        return flightResult;
    }

}
