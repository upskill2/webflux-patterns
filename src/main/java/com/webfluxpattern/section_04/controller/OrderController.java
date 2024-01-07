package com.webfluxpattern.section_04.controller;

import com.webfluxpattern.section_04.dto.request.OrderRequest;
import com.webfluxpattern.section_04.dto.response.OrderResponse;
import com.webfluxpattern.section_04.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ("/sec04")
public class OrderController {

    @Autowired
    private OrchestratorService service;

    @PostMapping ("/order")
    public Mono<ResponseEntity<OrderResponse>> placeOrder (@RequestBody Mono<OrderRequest> request) {
        return service.placeOrder (request)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.notFound ().build ());
    }

}
