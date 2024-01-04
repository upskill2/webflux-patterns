package com.webfluxpattern.section_02.client;

import com.webfluxpattern.section_02.dto.FlightResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

public interface FlightClient {

    Flux<FlightResult> getFlightResults (String from, String to);
}
