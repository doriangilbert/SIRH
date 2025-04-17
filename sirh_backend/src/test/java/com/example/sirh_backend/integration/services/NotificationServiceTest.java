package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Notification;
import com.example.sirh_backend.repositories.NotificationRepository;
import com.example.sirh_backend.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification testNotification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testNotification = new Notification();
        testNotification.setTitle("Test Notification");
        testNotification.setDescription("This is a test notification.");
    }

    @Test
    void getAllNotificationsReturnsAllNotifications() {
        when(notificationRepository.findAll()).thenReturn(List.of(testNotification));

        var notifications = notificationService.getAllNotifications();

        assertNotNull(notifications);
        assertEquals(1, notifications.size());
        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void getNotificationByIdReturnsCorrectNotification() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(testNotification));

        var notification = notificationService.getNotificationById(1L);

        assertNotNull(notification);
        assertEquals("Test Notification", notification.getTitle());
        verify(notificationRepository, times(1)).findById(1L);
    }

    @Test
    void getNotificationByIdThrowsNotFoundExceptionForInvalidId() {
        when(notificationRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> notificationService.getNotificationById(999L));
        verify(notificationRepository, times(1)).findById(999L);
    }

    @Test
    void createNotificationSavesAndReturnsNotification() {
        when(notificationRepository.save(testNotification)).thenReturn(testNotification);

        var savedNotification = notificationService.createNotification(testNotification);

        assertNotNull(savedNotification);
        assertEquals("Test Notification", savedNotification.getTitle());
        verify(notificationRepository, times(1)).save(testNotification);
    }

    @Test
    void updateNotificationUpdatesNotificationDetails() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(testNotification));
        when(notificationRepository.save(testNotification)).thenReturn(testNotification);

        testNotification.setTitle("Updated Notification");
        var updatedNotification = notificationService.updateNotification(1L, testNotification);

        assertEquals("Updated Notification", updatedNotification.getTitle());
        verify(notificationRepository, times(1)).findById(1L);
        verify(notificationRepository, times(1)).save(testNotification);
    }

    @Test
    void updateNotificationThrowsIllegalArgumentExceptionForNullUpdatedNotification() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(testNotification));

        assertThrows(IllegalArgumentException.class, () -> notificationService.updateNotification(1L, null));
        verify(notificationRepository, times(1)).findById(1L);
    }

    @Test
    void updateNotificationThrowsNotFoundExceptionForInvalidId() {
        when(notificationRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedNotification = new Notification();
        updatedNotification.setTitle("Invalid");

        assertThrows(NotFoundException.class, () -> notificationService.updateNotification(999L, updatedNotification));
        verify(notificationRepository, times(1)).findById(999L);
    }
}