package com.webfluxpattern.section_08.client;

import com.webfluxpattern.section_08.dto.ReviewResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ReviewClient {

    private final WebClient webClient;

    public ReviewClient (@Value ("${sec08.review.service}") String baseUrl) {
        this.webClient = WebClient.builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    @CircuitBreaker (name = "review-service", fallbackMethod = "getReviewFallback")
    public Mono<List<ReviewResponseDto>> getReview (int id) {
        return webClient
                .get ()
                .uri ("/{id}", id)
                .retrieve ()
                .onStatus (HttpStatus::is4xxClientError,
                        clientResponse -> Mono.empty ())
                .bodyToFlux (ReviewResponseDto.class)
                .collectList ()
               // .retryWhen (Retry.fixedDelay (3, Duration.ofMillis (100)))
                .retry (3)
                .timeout (Duration.ofMillis (100));

    }

    public Mono<List<ReviewResponseDto>> getReviewFallback (int id, Throwable throwable) {
        log.error ("Error occurred while calling review service: {}", throwable.getMessage ());
        return Mono.just (Collections.emptyList ());
    }

}
