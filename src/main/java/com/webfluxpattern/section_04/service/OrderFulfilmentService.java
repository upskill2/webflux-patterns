package com.webfluxpattern.section_04.service;

import com.webfluxpattern.section_04.client.ProductClient;
import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_04.dto.Status;
import com.webfluxpattern.section_04.dto.response.Product;
import com.webfluxpattern.section_04.service.impl.InventoryOrchestrator;
import com.webfluxpattern.section_04.service.impl.PaymentOrchestrator;
import com.webfluxpattern.section_04.service.impl.ShippingOrchestrator;
import com.webfluxpattern.section_04.util.OrchestrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderFulfilmentService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private PaymentOrchestrator paymentOrchestrator;
    @Autowired
    private ShippingOrchestrator shippingOrchestrator;
    @Autowired
    private InventoryOrchestrator inventoryOrchestrator;

    public Mono<OrchestrationRequestContext> placeOrder (OrchestrationRequestContext context) {

        return getProduct (context)
                .doOnNext (OrchestrationUtil::buildPaymentRequest)
                .flatMap (paymentOrchestrator::create)
                .doOnNext (OrchestrationUtil::buildInventoryRequest)
                .flatMap (inventoryOrchestrator::create)
                .doOnNext (OrchestrationUtil::buildShippingRequest)
                .flatMap (shippingOrchestrator::create)
                .doOnNext (i -> i.setStatus (Status.SUCCESS))
                .doOnError (i -> context.setStatus (Status.FAILED))
                .onErrorReturn (context);
    }

    private Mono<OrchestrationRequestContext> getProduct (OrchestrationRequestContext context) {
        final Mono<Product> product = productClient
                .getProduct (context.getOrderRequest ().getProductId ());
        return product
                .map (Product::getPrice)
                .doOnNext (context::setProductPrice)
                .map (i -> context);
    }

}
