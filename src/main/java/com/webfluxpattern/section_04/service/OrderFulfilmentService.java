package com.webfluxpattern.section_04.service;

import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_04.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

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
