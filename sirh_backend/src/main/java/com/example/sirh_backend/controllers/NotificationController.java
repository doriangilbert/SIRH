package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.NotificationDTO;
import com.example.sirh_backend.mappers.NotificationMapper;
import com.example.sirh_backend.models.entities.Notification;
import com.example.sirh_backend.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        return new ResponseEntity<>(NotificationMapper.toDTO(notificationService.getAllNotifications()), HttpStatus.OK);
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable long id) {
        return new ResponseEntity<>(NotificationMapper.toDTO(notificationService.getNotificationById(id)), HttpStatus.OK);
    }

    @PostMapping("/notifications")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody Notification notification) {
        return new ResponseEntity<>(NotificationMapper.toDTO(notificationService.createNotification(notification)), HttpStatus.CREATED);
    }

    @PutMapping("/notifications/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable long id, @RequestBody Notification notification) {
        return new ResponseEntity<>(NotificationMapper.toDTO(notificationService.updateNotification(id, notification)), HttpStatus.OK);
    }
}
