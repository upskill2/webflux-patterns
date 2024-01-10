package com.webfluxpattern.section_06.dto;

import lombok.Data;

@Data
public class ReviewResponseDto {

    private String user;
    private String comment;
    private int rating;
    private int id;
}
