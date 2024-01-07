package com.webfluxpattern.section_04.util;

import com.webfluxpattern.section_03.dto.OrchestrationRequestContext;
import com.webfluxpattern.section_03.dto.request.InventoryRequest;
import com.webfluxpattern.section_03.dto.request.PaymentRequest;
import com.webfluxpattern.section_03.dto.request.ShippingRequest;

public class OrchestrationUtil {

    public static void buildRequestContext (OrchestrationRequestContext context) {
        buildInventoryRequest (context);
        buildShippingRequest (context);
        buildPaymentRequest (context);
    }

    private static void buildPaymentRequest (OrchestrationRequestContext context) {
        PaymentRequest paymentRequest = PaymentRequest
                .create (context.getOrderRequest ().getUserId (),
                        (int) (context.getProductPrice () * context.getOrderRequest ().getQuantity ()),
                        context.getOrderId ());
        context.setPaymentRequest (paymentRequest);
    }

    private static void buildInventoryRequest (OrchestrationRequestContext context) {
        InventoryRequest inventoryRequest = InventoryRequest
                .create (context.getOrderId (),
                        context.getOrderRequest ().getProductId (),
                        context.getOrderRequest ().getQuantity ());
        context.setInventoryRequest (inventoryRequest);
    }

    private static void buildShippingRequest (OrchestrationRequestContext context) {
        ShippingRequest shippingRequest = ShippingRequest
                .create (context.getOrderRequest ().getQuantity (),
                        context.getOrderId (),
                        context.getOrderRequest ().getUserId ());
        context.setShippingRequest (shippingRequest);
    }

}
