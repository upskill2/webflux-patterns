package com.webfluxpattern.section_01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private String user;
    private String comment;
    private int rating;
    private int id;

}
