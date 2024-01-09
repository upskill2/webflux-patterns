package com.webfluxpattern.section_04.service.impl;

import com.webfluxpattern.section_04.client.UserClient;
import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_04.dto.Status;
import com.webfluxpattern.section_04.dto.response.PaymentResponse;
import com.webfluxpattern.section_04.service.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class PaymentOrchestrator extends Orchestrator {

    @Autowired
    private UserClient userClient;

    @Override
    public Mono<OrchestrationRequestContext> create (final OrchestrationRequestContext context) {
        final Mono<PaymentResponse> deduct = userClient.deduct (context.getPaymentRequest ());
        return deduct
                .doOnNext (context::setPaymentResponse)
                .thenReturn (context)
                .handle (statusHandler ());

    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess () {
        return context -> Objects.nonNull (context.getPaymentResponse ()) && context.getPaymentResponse ().getStatus () == Status.SUCCESS;
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel () {
        return context -> Mono.just (context)
                .filter (isSuccess ())
                .map (OrchestrationRequestContext::getPaymentRequest)
                .flatMap (userClient::refund)
                .subscribe ();

    }
}
