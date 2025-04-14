package com.example.sirh_backend.models;

public class PriorityNotificationDecorator extends NotificationDecorator {

    public PriorityNotificationDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public String getTitle() {
        return "[PRIORITY] " + super.getTitle();
    }
}
