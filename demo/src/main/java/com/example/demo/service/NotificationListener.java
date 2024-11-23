package com.example.demo.service;

import com.example.demo.model.Notification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class NotificationListener {

    private final RedisTemplate<String, Notification> redisTemplate;

    public NotificationListener(RedisTemplate<String, Notification> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.notification}")
    public void handleNotification(Notification notification) {
        System.out.println("Received notification: " + notification);

        // Simulate notification processing
        notification.setStatus("sent");
        redisTemplate.opsForHash().put("notifications", notification.getId(), notification);
        System.out.println("Notification processed and saved to Redis: " + notification);
    }
}
