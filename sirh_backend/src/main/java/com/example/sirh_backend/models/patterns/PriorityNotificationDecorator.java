package com.example.sirh_backend.models.patterns;

import com.example.sirh_backend.models.entities.Notification;

public class PriorityNotificationDecorator extends NotificationDecorator {

    public PriorityNotificationDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public String getTitle() {
        return "[PRIORITY] " + super.getTitle();
    }
}
