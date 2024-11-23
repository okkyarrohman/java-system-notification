package com.example.demo.service;

import com.example.demo.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue.notification}")
    private String notificationQueue;

    public NotificationPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(Notification notification) {
        rabbitTemplate.convertAndSend(notificationQueue, notification);
        System.out.println("Notification sent: " + notification);
    }
}
