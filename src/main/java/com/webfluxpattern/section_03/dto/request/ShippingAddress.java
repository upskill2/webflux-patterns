package com.webfluxpattern.section_03.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor (staticName = "create")
public class ShippingAddress {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
