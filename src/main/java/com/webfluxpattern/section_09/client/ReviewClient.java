package com.webfluxpattern.section_09.client;

import com.webfluxpattern.section_09.dto.ReviewResponseDto;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
public class ReviewClient {

    private final WebClient webClient;

    public ReviewClient (@Value ("${sec09.review.service}") String baseUrl) {
        this.webClient = WebClient.builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    @RateLimiter (name = "review-service", fallbackMethod = "getReviewFallback")
    public Mono<List<ReviewResponseDto>> getReview (int id) {
        return webClient
                .get ()
                .uri ("/{id}", id)
                .retrieve ()
                .onStatus (HttpStatus::is4xxClientError,
                        clientResponse -> Mono.empty ())
                .bodyToFlux (ReviewResponseDto.class)
                .collectList ();
    }

    public Mono<List<ReviewResponseDto>> getReviewFallback (int id, Exception e) {
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto ("1", "1", 1, 1);
        return Mono.just (Collections.singletonList (reviewResponseDto));
    }

}
