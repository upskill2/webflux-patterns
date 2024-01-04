package com.webfluxpattern.section_03.service;

import com.webfluxpattern.section_03.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_03.dto.Status;
import com.webfluxpattern.section_03.service.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFulfilmentService {

    @Autowired
    private List<Orchestrator> orchestrators;


    public Mono<OrchestrationRequestContext> placeOrder (OrchestrationRequestContext context) {

        final List<Mono<OrchestrationRequestContext>> publishers = orchestrators
                .stream ()
                .map (orchestrator -> orchestrator.create (context))
                .toList ();

        return Mono.zip (publishers, array -> array[0])
                .cast (OrchestrationRequestContext.class)
                .doOnNext (this::updateStatus);

    }

    private void updateStatus (OrchestrationRequestContext context) {
        Status finalStatus = orchestrators.stream ()
                .allMatch (orchestrator -> orchestrator.isSuccess ().test (context)) ? Status.SUCCESS : Status.FAILED;
        context.setStatus (finalStatus);
    }

}
