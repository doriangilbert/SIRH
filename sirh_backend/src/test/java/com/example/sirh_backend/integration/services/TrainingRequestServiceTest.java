package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Training;
import com.example.sirh_backend.models.entities.TrainingRequest;
import com.example.sirh_backend.models.enums.RequestStatus;
import com.example.sirh_backend.repositories.TrainingRequestRepository;
import com.example.sirh_backend.services.TrainingRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingRequestServiceTest {

    @Mock
    private TrainingRequestRepository trainingRequestRepository;

    @InjectMocks
    private TrainingRequestService trainingRequestService;

    private TrainingRequest testTrainingRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Employee reviewer = new Employee();
        reviewer.setFirstName("Jane");

        Training training = new Training();
        training.setName("Java Training");

        testTrainingRequest = new TrainingRequest();
        testTrainingRequest.setStatus(RequestStatus.PENDING);
        testTrainingRequest.setTraining(training);
        testTrainingRequest.setReviewer(reviewer);
    }

    @Test
    void getAllTrainingRequestsReturnsAllRequests() {
        when(trainingRequestRepository.findAll()).thenReturn(List.of(testTrainingRequest));

        var requests = trainingRequestService.getAllTrainingRequests();

        assertNotNull(requests);
        assertEquals(1, requests.size());
        verify(trainingRequestRepository, times(1)).findAll();
    }

    @Test
    void getTrainingRequestByIdReturnsCorrectRequest() {
        when(trainingRequestRepository.findById(1L)).thenReturn(Optional.of(testTrainingRequest));

        var request = trainingRequestService.getTrainingRequestById(1L);

        assertNotNull(request);
        assertEquals(RequestStatus.PENDING, request.getStatus());
        verify(trainingRequestRepository, times(1)).findById(1L);
    }

    @Test
    void getTrainingRequestByIdThrowsNotFoundExceptionForInvalidId() {
        when(trainingRequestRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> trainingRequestService.getTrainingRequestById(999L));
        verify(trainingRequestRepository, times(1)).findById(999L);
    }

    @Test
    void createTrainingRequestSavesAndReturnsRequest() {
        when(trainingRequestRepository.save(testTrainingRequest)).thenReturn(testTrainingRequest);

        var savedRequest = trainingRequestService.createTrainingRequest(testTrainingRequest);

        assertNotNull(savedRequest);
        assertEquals(RequestStatus.PENDING, savedRequest.getStatus());
        verify(trainingRequestRepository, times(1)).save(testTrainingRequest);
    }

    @Test
    void updateTrainingRequestUpdatesRequestDetails() {
        when(trainingRequestRepository.findById(1L)).thenReturn(Optional.of(testTrainingRequest));
        when(trainingRequestRepository.save(testTrainingRequest)).thenReturn(testTrainingRequest);

        testTrainingRequest.setStatus(RequestStatus.APPROVED);
        var updatedRequest = trainingRequestService.updateTrainingRequest(1L, testTrainingRequest);

        assertEquals(RequestStatus.APPROVED, updatedRequest.getStatus());
        verify(trainingRequestRepository, times(1)).findById(1L);
        verify(trainingRequestRepository, times(1)).save(testTrainingRequest);
    }
}