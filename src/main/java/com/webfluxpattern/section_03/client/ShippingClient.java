package com.webfluxpattern.section_03.client;

import com.webfluxpattern.section_03.dto.Status;
import com.webfluxpattern.section_03.dto.request.ShippingRequest;
import com.webfluxpattern.section_03.dto.response.ShippingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ShippingClient {

    private static final String SCHEDULE = "/schedule";
    private static final String CANCEL = "/cancel";

    private final WebClient webClient;

    public ShippingClient (@Value ("${sec03.shipping.service}") String baseUrl) {
        webClient = WebClient
                .builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    public Mono<ShippingResponse> schedule (ShippingRequest shippingRequest) {
        return callShippingService (SCHEDULE, shippingRequest);
    }

    public Mono<ShippingResponse> cancel (ShippingRequest shippingRequest) {
        return callShippingService (CANCEL, shippingRequest);
    }

    private Mono<ShippingResponse> callShippingService (final String uri, final ShippingRequest shippingRequest) {
        return webClient
                .post ()
                .uri (uri)
                .bodyValue (shippingRequest)
                .retrieve ()
                .bodyToMono (ShippingResponse.class)

                .onErrorReturn (this.responseOnError (shippingRequest));
    }

    private ShippingResponse responseOnError (ShippingRequest shippingRequest) {
        return ShippingResponse.builder ()
                .orderId (shippingRequest.getOrderId ())
                .quantity (shippingRequest.getQuantity ())
                .address (null)
                .expectedDelivery (null)
                .status (Status.FAILED)
                .build ();
    }

}
