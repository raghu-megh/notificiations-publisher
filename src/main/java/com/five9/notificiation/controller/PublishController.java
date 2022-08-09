package com.five9.notificiation.controller;

//import com.five9.avro.recording.upload.events.RecordingUploadResult;
//import com.five9.notificiation.EventType;
import com.five9.notificiation.RecordingUploadEvent;
import com.five9.notificiation.service.PublishService;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/publish")
public class PublishController {

    private final PublishService publisherService;

    @PostMapping("/start")
    public ResponseEntity<RecordingUploadEvent> start() throws Exception {
        String destination = "destination-path";
        String recordingFilename = "filename";

        RecordingUploadEvent event = RecordingUploadEvent.builder()
                .eventType(com.five9.notificiation.EventType.START)
                .domainId(String.valueOf(new Random().nextInt(1000)))
//                .domainId(String.valueOf(939))
                .destination(destination)
                .recordingId(UUID.randomUUID().toString())
                .recordingFilename(recordingFilename)
                .build();

        log.info("Publishing {}", new Gson().toJson(event));
        publisherService.publish(event);
        return ResponseEntity.ok(event);
    }

    @PostMapping(value = "/success", consumes = "application/json")
    public ResponseEntity<RecordingUploadEvent> success(@RequestBody String message) throws Exception {
        RecordingUploadEvent event = new Gson().fromJson(message, RecordingUploadEvent.class);
        publisherService.publish(event);
        return ResponseEntity.ok(event);
    }

}
