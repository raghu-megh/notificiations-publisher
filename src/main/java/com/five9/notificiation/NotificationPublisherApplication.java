package com.five9.notificiation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class NotificationPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationPublisherApplication.class, args);
    }
}
