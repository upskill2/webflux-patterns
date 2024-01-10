package com.webfluxpattern.section_09.service;


import com.webfluxpattern.section_09.client.ProductClient;
import com.webfluxpattern.section_09.client.ReviewClient;
import com.webfluxpattern.section_09.dto.ProductAggregatedDto;
import com.webfluxpattern.section_09.dto.ProductResponseDto;
import com.webfluxpattern.section_09.dto.ReviewResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductAggregatorService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private ReviewClient reviewClient;

    public Mono<ProductAggregatedDto> aggregateProduct (int id) {
        return Mono.zip (
                        productClient.getProduct (id),
                        reviewClient.getReview (id)
                )
                .map (tuple -> toDto (tuple.getT1 (), tuple.getT2 ()));
    }

    private ProductAggregatedDto toDto (ProductResponseDto productResponseDto,
                                        List<ReviewResponseDto> reviewResponseDtoList) {

        return ProductAggregatedDto.create (
                productResponseDto.getId (),
                productResponseDto.getCategory (),
                productResponseDto.getDescription (),
                reviewResponseDtoList
        );
    }

}
