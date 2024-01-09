package com.webfluxpattern.section_04.service;

import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_04.exception.OrderFulfillmentException;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class Orchestrator {

    public abstract Mono<OrchestrationRequestContext> create (OrchestrationRequestContext context);
    public abstract Predicate<OrchestrationRequestContext> isSuccess ();
    public abstract Consumer<OrchestrationRequestContext> cancel ();
    protected BiConsumer<OrchestrationRequestContext, SynchronousSink<OrchestrationRequestContext>> statusHandler () {
        return (context, sink) -> {
            final boolean test = isSuccess ().test (context);
            if (test) {
                sink.next (context);
            } else {
                sink.error (new OrderFulfillmentException ("Order Fulfillment Failed"));
            }
        };
    }
}
