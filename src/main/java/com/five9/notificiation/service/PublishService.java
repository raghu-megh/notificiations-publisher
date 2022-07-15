package com.five9.notificiation.service;

import com.five9.pubsub.Publisher;
import com.five9.pubsub.spring.EventBusPublisher;

import java.util.Collections;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublishService {

    @EventBusPublisher(topic = "first-topic")
    private Publisher<String> testTopicPublisher;

    public void publish(String msg) {
        log.info("publish msg:{}", msg);
        testTopicPublisher.publish(msg, Collections.emptyMap());
    }

}
