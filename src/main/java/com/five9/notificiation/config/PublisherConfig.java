package com.five9.notificiation.config;

import com.five9.notificiation.service.PublishService;

import org.springframework.context.annotation.Bean;

public class PublisherConfig {

    @Bean
    public PublishService publishService() {
        return new PublishService();
    }
}
