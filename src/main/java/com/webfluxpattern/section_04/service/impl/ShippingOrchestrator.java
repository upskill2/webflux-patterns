package com.webfluxpattern.section_04.service.impl;

import com.webfluxpattern.section_04.client.ShippingClient;
import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_04.dto.Status;
import com.webfluxpattern.section_04.service.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class ShippingOrchestrator extends Orchestrator {

    @Autowired
    private ShippingClient shippingClient;

    @Override
    public Mono<OrchestrationRequestContext> create (final OrchestrationRequestContext context) {
        return shippingClient.schedule (context.getShippingRequest ())
                .doOnNext (context::setShippingResponse)
                .thenReturn (context)
                .handle (statusHandler ());
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess () {
        return context -> Objects.nonNull (context.getShippingResponse ()) && context.getShippingResponse ().getStatus () == Status.SUCCESS;
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel () {
        return ctx -> Mono.just (ctx)
                .filter (isSuccess ())
                .map (OrchestrationRequestContext::getShippingRequest)
                .flatMap (shippingClient::cancel)
                .subscribe ();
    }
}
