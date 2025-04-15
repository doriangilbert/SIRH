package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.NotificationDTO;
import com.example.sirh_backend.mappers.NotificationMapper;
import com.example.sirh_backend.models.entities.Notification;
import com.example.sirh_backend.services.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public List<NotificationDTO> getAllNotifications() {
        return NotificationMapper.toDTO(notificationService.getAllNotifications());
    }

    @GetMapping("/notifications/{id}")
    public NotificationDTO getNotificationById(@PathVariable long id) {
        return NotificationMapper.toDTO(notificationService.getNotificationById(id));
    }

    @PostMapping("/notifications")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping("/notifications/{id}")
    public Notification updateNotification(@PathVariable long id, @RequestBody Notification notification) {
        return notificationService.updateNotification(id, notification);
    }
}
