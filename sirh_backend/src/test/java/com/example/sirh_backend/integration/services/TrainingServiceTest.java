package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Training;
import com.example.sirh_backend.repositories.TrainingRepository;
import com.example.sirh_backend.services.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingService trainingService;

    private Training testTraining;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testTraining = new Training();
        testTraining.setName("Java Training");
        testTraining.setDescription("Learn Java basics");
    }

    @Test
    void getAllTrainingsReturnsAllTrainings() {
        when(trainingRepository.findAll()).thenReturn(List.of(testTraining));

        var trainings = trainingService.getAllTrainings();

        assertNotNull(trainings);
        assertEquals(1, trainings.size());
        verify(trainingRepository, times(1)).findAll();
    }

    @Test
    void getTrainingByIdReturnsCorrectTraining() {
        when(trainingRepository.findById(1L)).thenReturn(Optional.of(testTraining));

        var training = trainingService.getTrainingById(1L);

        assertNotNull(training);
        assertEquals("Java Training", training.getName());
        verify(trainingRepository, times(1)).findById(1L);
    }

    @Test
    void getTrainingByIdThrowsNotFoundExceptionForInvalidId() {
        when(trainingRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> trainingService.getTrainingById(999L));
        verify(trainingRepository, times(1)).findById(999L);
    }

    @Test
    void createTrainingSavesAndReturnsTraining() {
        when(trainingRepository.save(testTraining)).thenReturn(testTraining);

        var savedTraining = trainingService.createTraining(testTraining);

        assertNotNull(savedTraining);
        assertEquals("Java Training", savedTraining.getName());
        verify(trainingRepository, times(1)).save(testTraining);
    }

    @Test
    void updateTrainingUpdatesTrainingDetails() {
        when(trainingRepository.findById(1L)).thenReturn(Optional.of(testTraining));
        when(trainingRepository.save(testTraining)).thenReturn(testTraining);

        testTraining.setName("Updated Training");
        testTraining.setDescription("Updated description");
        var updatedTraining = trainingService.updateTraining(1L, testTraining);

        assertEquals("Updated Training", updatedTraining.getName());
        assertEquals("Updated description", updatedTraining.getDescription());
        verify(trainingRepository, times(1)).findById(1L);
        verify(trainingRepository, times(1)).save(testTraining);
    }

    @Test
    void updateTrainingThrowsIllegalArgumentExceptionForNullUpdatedTraining() {
        when(trainingRepository.findById(1L)).thenReturn(Optional.of(testTraining));

        assertThrows(IllegalArgumentException.class, () -> trainingService.updateTraining(1L, null));
        verify(trainingRepository, times(1)).findById(1L);
    }

    @Test
    void updateTrainingThrowsNotFoundExceptionForInvalidId() {
        when(trainingRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedTraining = new Training();
        updatedTraining.setName("Invalid");

        assertThrows(NotFoundException.class, () -> trainingService.updateTraining(999L, updatedTraining));
        verify(trainingRepository, times(1)).findById(999L);
    }
}