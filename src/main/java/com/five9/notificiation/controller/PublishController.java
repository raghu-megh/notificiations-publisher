package com.five9.notificiation.controller;

import com.five9.avro.recording.upload.events.RecordingUploadQueued;
import com.five9.notificiation.service.PublishService;

import java.time.Instant;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/publish")
public class PublishController {

    private final PublishService publisherService;

    @PostMapping("/queued")
    public void queued() {
        String RecordingID = "recordingId";
        String DomainID = "domainId";
        String Destination = "destination-path";
        String FileName = "filename";
        Instant TimeStamp = Instant.now();

        RecordingUploadQueued message = RecordingUploadQueued.newBuilder().setRecordingId(RecordingID)
                        .setDomainId(DomainID).setDestination(Destination).setRecordingFilename(FileName)
                        .setQueuedTimestamp(TimeStamp).build();
        publisherService.publish(String.valueOf(message));
    }

}
