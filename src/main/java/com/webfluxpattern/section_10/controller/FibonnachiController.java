package com.webfluxpattern.section_10.controller;

import com.webfluxpattern.section_10.config.SchedulerConfig;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping ("/sec10")
public class FibonnachiController {

    private final Scheduler scheduler;

    @Autowired
    public FibonnachiController(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    @GetMapping ("fib/{input}")

    public Mono<ResponseEntity<Long>> fib (@PathVariable Long input) {
        return Mono.fromSupplier (()-> findFibonnachi (input))
                .subscribeOn (scheduler)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.status (HttpStatus.TOO_MANY_REQUESTS).build ());
    }

    private Long findFibonnachi (Long input) {
        if (input <= 1) {
            return input;
        }
        return findFibonnachi (input - 1) + findFibonnachi (input - 2);
    }


}
