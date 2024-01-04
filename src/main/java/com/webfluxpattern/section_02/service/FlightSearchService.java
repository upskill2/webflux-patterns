package com.webfluxpattern.section_02.service;

import com.webfluxpattern.section_02.client.impl.DeltaClient;
import com.webfluxpattern.section_02.client.impl.FrontierClient;
import com.webfluxpattern.section_02.client.impl.JetBlueClient;
import com.webfluxpattern.section_02.dto.FlightResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Slf4j
public class FlightSearchService {

    @Autowired
    private DeltaClient deltaClient;
    @Autowired
    private FrontierClient frontierClient;
    @Autowired
    private JetBlueClient jetBlueClient;

    public Flux<FlightResult> searchFlight (String origin, String destination) {
        return Flux.merge (
                deltaClient.getFlightResults (origin, destination),
                frontierClient.getFlightResults (origin, destination),
                jetBlueClient.getFlightResults (origin, destination)
        ).take (Duration.ofSeconds (3L));

    }


}
