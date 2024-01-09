package com.webfluxpattern.section_04.client;

import com.webfluxpattern.section_04.dto.Status;
import com.webfluxpattern.section_04.dto.request.InventoryRequest;
import com.webfluxpattern.section_04.dto.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.webfluxpattern.section_04.client.UserClient.DEDUCT;

@Service
public class InventoryClient {


    public static final String RESTORE = "/restore";
    private final WebClient webClient;

    public InventoryClient (@Value ("${sec04.inventory.service}") String baseUrl) {
        webClient = WebClient
                .builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    public Mono<InventoryResponse> deduct (InventoryRequest inventoryRequest) {
        return callInventoryService (DEDUCT, inventoryRequest);
    }

    public Mono<InventoryResponse> restore (InventoryRequest inventoryRequest) {
        return callInventoryService (RESTORE, inventoryRequest);
    }

    private Mono<InventoryResponse> callInventoryService (final String uri, final InventoryRequest inventoryRequest) {
        return webClient
                .post ()
                .uri (uri)
                .bodyValue (inventoryRequest)
                .retrieve ()
                .bodyToMono (InventoryResponse.class)
                .onErrorReturn (this.responseOnError (inventoryRequest));
    }

    private InventoryResponse responseOnError (InventoryRequest inventoryRequest) {
        return InventoryResponse.builder ()
                .inventoryId (null)
                .productId (inventoryRequest.getProductId ())
                .quantity (inventoryRequest.getQuantity ())
                .remainingQuantity (0)
                .status (Status.FAILED)
                .build ();
    }


}
