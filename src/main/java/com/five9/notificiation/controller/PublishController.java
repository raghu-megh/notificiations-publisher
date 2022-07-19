package com.five9.notificiation.controller;

import com.five9.avro.recording.upload.events.RecordingUploadQueued;
import com.five9.avro.recording.upload.events.RecordingUploadSuccess;
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
        String Q_RecordingID = "recordingId1";
        String Q_DomainID = "domainId1";
        String Q_Destination = "destination-path1";
        String Q_FileName = "filename1";
        Instant Q_TimeStamp = Instant.now();

        RecordingUploadQueued message = RecordingUploadQueued.newBuilder().setRecordingId(Q_RecordingID)
                        .setDomainId(Q_DomainID).setDestination(Q_Destination).setRecordingFilename(Q_FileName)
                        .setQueuedTimestamp(Q_TimeStamp).build();
        publisherService.publish(String.valueOf(message));
    }

    @PostMapping("/success")
    public void success() {
        String S_RecordingID = "recordingId2";
        String S_DomainID = "domainId2";
        String S_Destination = "destination-path2";
        String S_FileName = "filename2";
        Integer S_Attempts = 5;
        Instant S_queuedTimestamp = Instant.now();
        Instant S_startTimestamp = Instant.now();
        Instant S_endTimestamp = Instant.now();

        RecordingUploadSuccess message = RecordingUploadSuccess.newBuilder().setRecordingId(S_RecordingID)
                .setDomainId(S_DomainID).setDestination(S_Destination).setRecordingFilename(S_FileName)
                .setQueuedTimestamp(S_queuedTimestamp).setAttempts(S_Attempts).setStartTimestamp(S_startTimestamp).setEndTimestamp(S_endTimestamp).build();
        publisherService.publish(String.valueOf(message));
    }

}
