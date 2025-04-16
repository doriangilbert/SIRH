package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.NotificationDTO;
import com.example.sirh_backend.models.entities.Notification;

import java.util.List;

public class NotificationMapper {

    private NotificationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static NotificationDTO toDTO(Notification notification) {
        if (notification != null) {
            return new NotificationDTO(
                    notification.getId(),
                    notification.getTitle(),
                    notification.getDescription(),
                    notification.getEmployee() != null ? notification.getEmployee().getId() : null
            );
        }
        return null;
    }

    public static List<NotificationDTO> toDTO(List<Notification> notifications) {
        return notifications.stream()
                .map(NotificationMapper::toDTO)
                .toList();
    }
}
