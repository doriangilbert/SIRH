package com.example.sirh_backend.services;

import com.example.sirh_backend.models.Notification;
import com.example.sirh_backend.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(long id, Notification updatedNotification) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setTitle(updatedNotification.getTitle());
            notification.setDescription(updatedNotification.getDescription());
            notification.setEmployee(updatedNotification.getEmployee());
            return notificationRepository.save(notification);
        }
        return null;
    }
}
