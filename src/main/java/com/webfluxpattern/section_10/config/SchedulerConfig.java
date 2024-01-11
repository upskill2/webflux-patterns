package com.webfluxpattern.section_10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class SchedulerConfig {

    @Bean
    public Scheduler parallelScheduler() {
        return Schedulers.newParallel("parallel-scheduler", 2);
    }
}
