package com.webfluxpattern.section_01.client;

import com.webfluxpattern.section_01.dto.PromotionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PromotionClient {

    private final WebClient webClient;

    public PromotionClient (@Value ("${sec01.promotion.service}") String baseUrl) {
        this.webClient = WebClient.builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    public Mono<PromotionResponseDto> getPromotion (int id) {
        return webClient
                .get ()
                .uri ("/{id}", id)
                .retrieve ()
                .bodyToMono (PromotionResponseDto.class)
                .onErrorReturn (this.fallback (id));
    }

    private PromotionResponseDto fallback (int id) {
        return PromotionResponseDto.create (id, "No promotion available", 0, null);
    }

}
