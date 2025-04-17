package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Feedback;
import com.example.sirh_backend.repositories.FeedbackRepository;
import com.example.sirh_backend.services.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback testFeedback;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testFeedback = new Feedback();
        testFeedback.setDescription("Great work on the project!");
    }

    @Test
    void getAllFeedbacksReturnsAllFeedbacks() {
        when(feedbackRepository.findAll()).thenReturn(List.of(testFeedback));

        var feedbacks = feedbackService.getAllFeedbacks();

        assertNotNull(feedbacks);
        assertEquals(1, feedbacks.size());
        assertEquals("Great work on the project!", feedbacks.getFirst().getDescription());
        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    void getFeedbackByIdReturnsCorrectFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(testFeedback));

        var feedback = feedbackService.getFeedbackById(1L);

        assertNotNull(feedback);
        assertEquals("Great work on the project!", feedback.getDescription());
        verify(feedbackRepository, times(1)).findById(1L);
    }

    @Test
    void getFeedbackByIdThrowsNotFoundExceptionForInvalidId() {
        when(feedbackRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> feedbackService.getFeedbackById(999L));
        verify(feedbackRepository, times(1)).findById(999L);
    }

    @Test
    void createFeedbackSavesAndReturnsFeedback() {
        when(feedbackRepository.save(testFeedback)).thenReturn(testFeedback);

        var savedFeedback = feedbackService.createFeedback(testFeedback);

        assertNotNull(savedFeedback);
        assertEquals("Great work on the project!", savedFeedback.getDescription());
        verify(feedbackRepository, times(1)).save(testFeedback);
    }

    @Test
    void updateFeedbackUpdatesFeedbackDetails() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(testFeedback));
        when(feedbackRepository.save(testFeedback)).thenReturn(testFeedback);

        testFeedback.setDescription("Updated feedback description");
        var updatedFeedback = feedbackService.updateFeedback(1L, testFeedback);

        assertEquals("Updated feedback description", updatedFeedback.getDescription());
        verify(feedbackRepository, times(1)).findById(1L);
        verify(feedbackRepository, times(1)).save(testFeedback);
    }

    @Test
    void updateFeedbackThrowsIllegalArgumentExceptionForNullUpdatedFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(testFeedback));

        assertThrows(IllegalArgumentException.class, () -> feedbackService.updateFeedback(1L, null));
        verify(feedbackRepository, times(1)).findById(1L);
    }

    @Test
    void updateFeedbackThrowsNotFoundExceptionForInvalidId() {
        when(feedbackRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedFeedback = new Feedback();
        updatedFeedback.setDescription("Invalid update");

        assertThrows(NotFoundException.class, () -> feedbackService.updateFeedback(999L, updatedFeedback));
        verify(feedbackRepository, times(1)).findById(999L);
    }
}