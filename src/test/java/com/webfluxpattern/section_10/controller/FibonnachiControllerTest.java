package com.webfluxpattern.section_10.controller;

import com.webfluxpattern.BulkheadPatternApplication_Section_10;
import com.webfluxpattern.section_10.dto.ProductResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (classes = BulkheadPatternApplication_Section_10.class)
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class FibonnachiControllerTest {

    private WebClient webClient;

    @BeforeAll
    public void setUp () {
        webClient = WebClient.builder ()
                .baseUrl ("http://localhost:8080/sec10")
                .build ();
    }


    @Test
    void fib () {
        StepVerifier.create (Flux.merge (fibRequest (), productRequest ()))
                .verifyComplete ();
    }

    private Mono<Void> fibRequest () {
        return Flux.range (1, 40)
                .flatMap (i -> webClient.get ().uri ("/fib/45")
                        .retrieve ()
                        .bodyToMono (Long.class))
                .doOnNext (k -> log.info ("{} -> {}", LocalDateTime.now (), k))
                .then ();

    }

    private Mono<Void> productRequest () {
        return Mono.delay (Duration.ofMillis (100))
                .thenMany (Flux.range (1, 40))
                .flatMap (i -> webClient.get ().uri ("/product/1")
                        .retrieve ()
                        .bodyToMono (ProductResponseDto.class))
                .map (ProductResponseDto::getCategory)
                .doOnNext (log::info)
                .then ();

    }

    private void print (Object o) {
        log.info ("{} -> {}", LocalDateTime.now (), Objects.toString (o));
    }

}