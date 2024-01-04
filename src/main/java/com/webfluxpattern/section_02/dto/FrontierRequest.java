package com.webfluxpattern.section_02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor (staticName = "create")
@NoArgsConstructor
public class FrontierRequest {

    private String from;
    private String to;
}
