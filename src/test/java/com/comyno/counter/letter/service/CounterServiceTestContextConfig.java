package com.comyno.counter.letter.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CounterServiceTestContextConfig {

    @Bean
    public CounterService charCounterService() {
        return new CounterService();
    }

}