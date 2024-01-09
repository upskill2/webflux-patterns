package com.webfluxpattern.section_04.util;

import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_04.dto.request.InventoryRequest;
import com.webfluxpattern.section_04.dto.request.PaymentRequest;
import com.webfluxpattern.section_04.dto.request.ShippingRequest;

public class OrchestrationUtil {

    public static void buildPaymentRequest (OrchestrationRequestContext context) {
        PaymentRequest paymentRequest = PaymentRequest
                .create (
                        context.getOrderRequest ().getQuantity ()* context.getProductPrice (),
                        context.getOrderId (),
                        context.getOrderRequest ().getUserId ()
                );
        context.setPaymentRequest (paymentRequest);
    }

    public static void buildInventoryRequest (OrchestrationRequestContext context) {
        InventoryRequest inventoryRequest = InventoryRequest
                .create (context.getPaymentResponse ().getPaymentId (),
                        context.getOrderRequest ().getProductId (),
                        context.getOrderRequest ().getQuantity ());
        context.setInventoryRequest (inventoryRequest);
    }

    public static void buildShippingRequest (OrchestrationRequestContext context) {
        ShippingRequest shippingRequest = ShippingRequest
                .create (context.getOrderRequest ().getQuantity (),
                        context.getInventoryResponse ().getInventoryId (),
                        context.getOrderRequest ().getUserId ());
        context.setShippingRequest (shippingRequest);
    }

}
