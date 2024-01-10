package com.webfluxpattern.section_08.client;

import com.webfluxpattern.section_08.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private final WebClient webClient;


    public ProductClient (@Value ("${sec08.product.service}") String baseUrl) {
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
