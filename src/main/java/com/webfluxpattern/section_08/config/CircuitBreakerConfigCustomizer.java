package com.webfluxpattern.section_08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfigCustomizer {

    @Bean
    public io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer circuitBreakerConfig () {
        return io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer.of ("review-service", builder ->
                builder
                        .slidingWindowSize (3)
/*                        .failureRateThreshold (50)
                        .waitDurationInOpenState (java.time.Duration.ofMillis (1000))
                        .permittedNumberOfCallsInHalfOpenState (2)*/
        );

    }
}
