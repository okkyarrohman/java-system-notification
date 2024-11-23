package com.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.domain.model.Notification;
import com.domain.service.NotificationPublisher;

import java.util.UUID;

@RestController
@RequestMapping("/api/notification")

public class NotificationController {

    @GetMapping
    public String Welcome() {
        return "Welcome Guys";
    }

    private final NotificationPublisher notificationPublisher;

    public NotificationController(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @PostMapping
    public String sendNotification(@RequestBody Notification notification) {
        notification.setId(UUID.randomUUID().toString());
        notification.setStatus("pending");
        notificationPublisher.sendNotification(notification);
        return "Notification sent!";
    }
}
