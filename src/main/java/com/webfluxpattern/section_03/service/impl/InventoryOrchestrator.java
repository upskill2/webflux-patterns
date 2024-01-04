package com.webfluxpattern.section_03.service.impl;

import com.webfluxpattern.section_03.client.InventoryClient;
import com.webfluxpattern.section_03.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_03.dto.Status;
import com.webfluxpattern.section_03.service.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class InventoryOrchestrator extends Orchestrator {

    @Autowired
    private InventoryClient inventoryClient;
    @Override
    public Mono<OrchestrationRequestContext> create (final OrchestrationRequestContext context) {
        return inventoryClient.deduct (context.getInventoryRequest ())
                .doOnNext (context::setInventoryResponse)
                .thenReturn (context);
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess () {
        return context -> context.getInventoryResponse ().getStatus () == Status.SUCCESS;
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel () {
        return context -> Mono.just (context)
                .filter (isSuccess ())
                .map (OrchestrationRequestContext::getInventoryRequest)
                .flatMap (inventoryClient::restore)
                .subscribe ();
    }
}
