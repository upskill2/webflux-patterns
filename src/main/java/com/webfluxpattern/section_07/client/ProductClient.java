package com.webfluxpattern.section_07.client;

import com.webfluxpattern.section_07.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductClient {

    private final WebClient webClient;


    public ProductClient (@Value ("${sec07.product.service}") String baseUrl) {
        this.webClient = WebClient.builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    public Mono<ProductResponseDto> getProduct (int id) {
       return webClient
                .get ()
                .uri ("/{id}", id)
                .retrieve ()
                .bodyToMono (ProductResponseDto.class)
               .onErrorResume (error -> Mono.empty ());
    }



}
