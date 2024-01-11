package com.webfluxpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.webfluxpattern.section_10")
public class BulkheadPatternApplication_Section_10 {

    public static void main (String[] args) {
        SpringApplication.run (BulkheadPatternApplication_Section_10.class, args);
    }

}
