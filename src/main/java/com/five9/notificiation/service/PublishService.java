package com.five9.notificiation.service;

import com.five9.notificiation.RecordingUploadEvent;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublishService {

    String projectId = "notifications-project-358320";
    String topicId = "notifications.events";

    public void publish(RecordingUploadEvent msg) throws Exception {
        TopicName topicName = TopicName.of(projectId, topicId);
        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();

            String message = new Gson().toJson(msg);
            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Published message ID: " + messageId);
        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }

    }

}
