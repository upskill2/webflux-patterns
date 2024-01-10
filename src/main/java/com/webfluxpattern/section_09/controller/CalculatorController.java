package com.webfluxpattern.section_09.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ("/sec09")
public class CalculatorController {

    @GetMapping ("calculator/{input}")
    @RateLimiter (name = "calculator-service", fallbackMethod = "calcFallback")
    public Mono<ResponseEntity<Integer>> doubleInput (@PathVariable Integer input) {
        return Mono.just (ResponseEntity.ok (input * 2));
    }

    public Mono<ResponseEntity<String>> calcFallback (Integer input, Throwable throwable) {
        return Mono.just (ResponseEntity.status (HttpStatus.TOO_MANY_REQUESTS).body ("Number of requests: " + input));

    }

}
