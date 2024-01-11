package com.webfluxpattern.section_09.controller;

import com.webfluxpattern.section_09.dto.ProductAggregatedDto;
import com.webfluxpattern.section_09.service.ProductAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ("/sec09")
public class ProductController {

    @Autowired
    private ProductAggregatorService productAggregatorService;

    @GetMapping ("/product/{id}")
    public Mono<ResponseEntity<ProductAggregatedDto>> getProductAggregate (@PathVariable Integer id) {

        return productAggregatorService.aggregateProduct (id)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.notFound ().build ());

    }

}