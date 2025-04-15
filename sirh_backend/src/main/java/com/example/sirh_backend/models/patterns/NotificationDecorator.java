package com.example.sirh_backend.models.patterns;

import com.example.sirh_backend.models.entities.Notification;

public abstract class NotificationDecorator extends Notification {

    private final Notification notification;

    public NotificationDecorator(Notification notification) {
        super(notification.getTitle(), notification.getDescription(), notification.getEmployee());
        this.notification = notification;
    }

    @Override
    public String getTitle() {
        return notification.getTitle();
    }
}
