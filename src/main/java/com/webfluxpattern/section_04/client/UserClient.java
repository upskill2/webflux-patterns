package com.webfluxpattern.section_04.client;

import com.webfluxpattern.section_04.dto.Status;
import com.webfluxpattern.section_04.dto.request.PaymentRequest;
import com.webfluxpattern.section_04.dto.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserClient {

    public static final String DEDUCT = "/deduct";
    public static final String REFUND = "/refund";
    private final WebClient webClient;

    public UserClient (@Value ("${sec04.user.service}") String baseUrl) {
        webClient = WebClient
                .builder ()
                .baseUrl (baseUrl)
                .build ();
    }

    public Mono<PaymentResponse> deduct (PaymentRequest paymentRequest) {
        return callUserService (DEDUCT, paymentRequest);
    }

    public Mono<PaymentResponse> refund (PaymentRequest paymentRequest) {
        return callUserService (REFUND, paymentRequest);
    }

    private Mono<PaymentResponse> callUserService (final String uri, final PaymentRequest paymentRequest) {
        return webClient
                .post ()
                .uri (uri)
                .bodyValue (paymentRequest)
                .retrieve ()
                .bodyToMono (PaymentResponse.class)
                .onErrorReturn (this.responseOnError (paymentRequest));
    }

    private PaymentResponse responseOnError (PaymentRequest paymentRequest) {
        return PaymentResponse.builder ()
                .paymentId (null)
                .userId (paymentRequest.getUserId ())
                .name (null)
                .balance (paymentRequest.getAmount ())
                .status (Status.FAILED)
                .build ();

    }

}
