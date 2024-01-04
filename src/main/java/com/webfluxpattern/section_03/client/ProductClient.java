package com.webfluxpattern.section_03.client;

import com.webfluxpattern.section_03.dto.response.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {
    private final WebClient webClient;

    public ProductClient (@Value ("${sec03.product.service}") String baseUrl) {
        this.webClient = WebClient
                .builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    public Mono<Product> getProduct (int id) {
        return webClient
                .get ()
                .uri ("/{id}", id)
                .retrieve ()
                .bodyToMono (Product.class)
                .onErrorResume (er -> Mono.empty ());
    }

}
