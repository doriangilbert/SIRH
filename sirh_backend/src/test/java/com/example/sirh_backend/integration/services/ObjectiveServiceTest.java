package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.entities.Objective;
import com.example.sirh_backend.repositories.ObjectiveRepository;
import com.example.sirh_backend.services.ObjectiveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObjectiveServiceTest {

    @Mock
    private ObjectiveRepository objectiveRepository;

    @InjectMocks
    private ObjectiveService objectiveService;

    private Objective testObjective;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testObjective = new Objective();
        testObjective.setDescription("Complete project");
        testObjective.setAchieved(false);
        testObjective.setEvaluation(new Evaluation());
    }

    @Test
    void getAllObjectivesReturnsAllObjectives() {
        when(objectiveRepository.findAll()).thenReturn(List.of(testObjective));

        var objectives = objectiveService.getAllObjectives();

        assertNotNull(objectives);
        assertEquals(1, objectives.size());
        verify(objectiveRepository, times(1)).findAll();
    }

    @Test
    void getObjectiveByIdReturnsCorrectObjective() {
        when(objectiveRepository.findById(1L)).thenReturn(Optional.of(testObjective));

        var objective = objectiveService.getObjectiveById(1L);

        assertNotNull(objective);
        assertEquals("Complete project", objective.getDescription());
        verify(objectiveRepository, times(1)).findById(1L);
    }

    @Test
    void getObjectiveByIdThrowsNotFoundExceptionForInvalidId() {
        when(objectiveRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> objectiveService.getObjectiveById(999L));
        verify(objectiveRepository, times(1)).findById(999L);
    }

    @Test
    void createObjectiveSavesAndReturnsObjective() {
        when(objectiveRepository.save(testObjective)).thenReturn(testObjective);

        var savedObjective = objectiveService.createObjective(testObjective);

        assertNotNull(savedObjective);
        assertEquals("Complete project", savedObjective.getDescription());
        verify(objectiveRepository, times(1)).save(testObjective);
    }

    @Test
    void updateObjectiveUpdatesObjectiveDetails() {
        when(objectiveRepository.findById(1L)).thenReturn(Optional.of(testObjective));
        when(objectiveRepository.save(testObjective)).thenReturn(testObjective);

        testObjective.setDescription("Updated project");
        testObjective.setAchieved(true);
        var updatedObjective = objectiveService.updateObjective(1L, testObjective);

        assertEquals("Updated project", updatedObjective.getDescription());
        assertTrue(updatedObjective.isAchieved());
        verify(objectiveRepository, times(1)).findById(1L);
        verify(objectiveRepository, times(1)).save(testObjective);
    }

    @Test
    void updateObjectiveThrowsIllegalArgumentExceptionForNullUpdatedObjective() {
        when(objectiveRepository.findById(1L)).thenReturn(Optional.of(testObjective));

        assertThrows(IllegalArgumentException.class, () -> objectiveService.updateObjective(1L, null));
        verify(objectiveRepository, times(1)).findById(1L);
    }

    @Test
    void updateObjectiveThrowsNotFoundExceptionForInvalidId() {
        when(objectiveRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedObjective = new Objective();
        updatedObjective.setDescription("Invalid");

        assertThrows(NotFoundException.class, () -> objectiveService.updateObjective(999L, updatedObjective));
        verify(objectiveRepository, times(1)).findById(999L);
    }
}