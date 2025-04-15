package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.NotificationDTO;
import com.example.sirh_backend.models.entities.Notification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

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
                .collect(Collectors.toList());
    }
}
