package com.webfluxpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.webfluxpattern.section_07")
public class RetryPatternApplication_Section_07 {

    public static void main (String[] args) {
        SpringApplication.run (RetryPatternApplication_Section_07.class, args);
    }

}
