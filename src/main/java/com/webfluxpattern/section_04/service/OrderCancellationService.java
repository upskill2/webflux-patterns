package com.webfluxpattern.section_04.service;

import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrderCancellationService {

    @Autowired
    private List<Orchestrator> orchestrators;

    private Sinks.Many<OrchestrationRequestContext> sink;
    private Flux<OrchestrationRequestContext> flux;

    @PostConstruct
    public void init () {
        sink = Sinks.many ().multicast ().onBackpressureBuffer ();
        flux = sink.asFlux ().publishOn (Schedulers.boundedElastic ());
        orchestrators.forEach (orchestrator -> flux.subscribe (orchestrator.cancel ()));
    }

    public void cancelOrder (OrchestrationRequestContext context) {
        sink.tryEmitNext (context);
    }

}
