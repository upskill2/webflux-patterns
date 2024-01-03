package com.webfluxpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.webfluxpattern.section_01")
public class WebfluxPatternApplication {

    public static void main (String[] args) {
        SpringApplication.run (WebfluxPatternApplication.class, args);
    }

}
