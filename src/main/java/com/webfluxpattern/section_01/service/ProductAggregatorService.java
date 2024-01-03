package com.webfluxpattern.section_01.service;


import com.webfluxpattern.section_01.client.ProductClient;
import com.webfluxpattern.section_01.client.PromotionClient;
import com.webfluxpattern.section_01.client.ReviewClient;
import com.webfluxpattern.section_01.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductAggregatorService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private PromotionClient promotionClient;
    @Autowired
    private ReviewClient reviewClient;

    public Mono<ProductAggregatedDto> aggregateProduct (int id) {
        return Mono.zip (
                        promotionClient.getPromotion (id),
                        productClient.getProduct (id),
                        reviewClient.getReview (id)
                )
                .map (tuple -> toDto (tuple.getT1 (), tuple.getT2 (), tuple.getT3 ()));

    }


    private ProductAggregatedDto toDto (PromotionResponseDto promotionResponseDto, ProductResponseDto productResponseDto,
                                        List<ReviewResponseDto> reviewResponseDtoList) {

        Price price = Price.builder ()
                .discount (promotionResponseDto.getDiscount ())
                .endDate (promotionResponseDto.getEndDate ())
                .initialPrice (productResponseDto.getPrice ())
                .savings (productResponseDto.getPrice () - productResponseDto.getPrice () * promotionResponseDto.getDiscount () / 100)
                .finalPrice (productResponseDto.getPrice () * promotionResponseDto.getDiscount () / 100)
                .build ();


        return ProductAggregatedDto.create (
                productResponseDto.getId (),
                productResponseDto.getCategory (),
                productResponseDto.getDescription (),
                price,
                reviewResponseDtoList
        );
    }

}
