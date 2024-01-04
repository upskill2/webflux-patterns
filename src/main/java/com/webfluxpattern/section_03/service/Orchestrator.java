package com.webfluxpattern.section_03.service;

import com.webfluxpattern.section_03.dto.OrchestrationRequestContext;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class Orchestrator {
    public abstract Mono<OrchestrationRequestContext> create (OrchestrationRequestContext context);

    public abstract Predicate<OrchestrationRequestContext> isSuccess ();

    public abstract Consumer<OrchestrationRequestContext> cancel ();

}
