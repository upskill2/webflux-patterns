package com.webfluxpattern.section_07.client;

import com.webfluxpattern.section_07.dto.ReviewResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewClient {

    private final WebClient webClient;

    public ReviewClient (@Value ("${sec07.review.service}") String baseUrl) {
        this.webClient = WebClient.builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    public Mono<List<ReviewResponseDto>> getReview (int id) {
        return webClient
                .get ()
                .uri ("/{id}", id)
                .retrieve ()
                .onStatus (HttpStatus::is4xxClientError,
                        clientResponse -> Mono.empty ())
                .bodyToFlux (ReviewResponseDto.class)
                .collectList ()
                .retry (5)
                .timeout (Duration.ofMillis (500))
                .onErrorReturn (Collections.emptyList ());
    }

}
