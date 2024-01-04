package com.webfluxpattern.section_03.service;

import com.webfluxpattern.section_03.client.ProductClient;
import com.webfluxpattern.section_03.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_03.dto.Status;
import com.webfluxpattern.section_03.dto.request.OrderRequest;
import com.webfluxpattern.section_03.dto.request.ShippingAddress;
import com.webfluxpattern.section_03.dto.response.OrderResponse;
import com.webfluxpattern.section_03.dto.response.Product;
import com.webfluxpattern.section_03.util.DebugUtil;
import com.webfluxpattern.section_03.util.OrchestrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class OrchestratorService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderFulfilmentService fulfilmentService;

    @Autowired
    private OrderCancellationService cancellationService;


    public Mono<OrderResponse> placeOrder (Mono<OrderRequest> request) {

        return request.map (OrchestrationRequestContext::new)
                .flatMap (this::getProduct)
                .doOnNext (OrchestrationUtil::buildRequestContext)
                .flatMap (fulfilmentService::placeOrder)
                .doOnNext (this::doOrderPostProcessing)
                .doOnNext (DebugUtil::printContext)
                .map (this::toOrderResponse);

    }

    private OrderResponse toOrderResponse (OrchestrationRequestContext context) {


        ShippingAddress address = context.getStatus () == Status.SUCCESS ? context.getShippingResponse ().getAddress () : null;
        LocalDate deliveryDate = context.getStatus () == Status.SUCCESS ? context.getShippingResponse ().getDeliveryDate () : null;

        return OrderResponse.builder ()
                .userId (context.getOrderRequest ().getUserId ())
                .productId (context.getOrderRequest ().getProductId ())
                .orderId (context.getOrderId ())
                .status (context.getStatus ())
                .shippingAddress (address)
                .deliveryDate (deliveryDate)
                .build ();
    }

    private void doOrderPostProcessing (OrchestrationRequestContext orchestrationRequestContext) {

        if (orchestrationRequestContext.getStatus () == Status.FAILED) {
            cancellationService.cancelOrder (orchestrationRequestContext);
        }
    }

    private Mono<OrchestrationRequestContext> getProduct (OrchestrationRequestContext context) {
        return productClient.getProduct (context.getOrderRequest ().getProductId ())
                .map (Product::getPrice)
                .doOnNext (context::setProductPrice)
                .thenReturn (context);

    }

}
