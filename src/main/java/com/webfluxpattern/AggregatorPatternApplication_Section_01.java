package com.webfluxpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.webfluxpattern.section_01")
public class AggregatorPatternApplication_Section_01 {

    public static void main (String[] args) {
        SpringApplication.run (AggregatorPatternApplication_Section_01.class, args);
    }

}
