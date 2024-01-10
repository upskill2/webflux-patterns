package com.webfluxpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.webfluxpattern.section_08")
public class CircuitBrakerPatternApplication_Section_08 {

    public static void main (String[] args) {
        SpringApplication.run (CircuitBrakerPatternApplication_Section_08.class, args);
    }

}
