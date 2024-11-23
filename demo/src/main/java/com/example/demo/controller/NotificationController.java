package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

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
