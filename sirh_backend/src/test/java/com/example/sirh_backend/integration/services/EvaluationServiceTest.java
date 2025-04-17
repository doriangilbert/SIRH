package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.enums.EvaluationStatus;
import com.example.sirh_backend.repositories.EvaluationRepository;
import com.example.sirh_backend.services.EvaluationService;
import com.example.sirh_backend.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EvaluationServiceTest {

    @Mock
    private EvaluationRepository evaluationRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private EvaluationService evaluationService;

    private Evaluation testEvaluation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testEvaluation = new Evaluation();
        testEvaluation.setYear(Year.of(2025));
        testEvaluation.setDescription("Annual performance evaluation");
        testEvaluation.setStatus(EvaluationStatus.ONGOING);

        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEvaluation.setEmployee(testEmployee);
    }

    @Test
    void getAllEvaluationsReturnsAllEvaluations() {
        when(evaluationRepository.findAll()).thenReturn(List.of(testEvaluation));

        var evaluations = evaluationService.getAllEvaluations();

        assertNotNull(evaluations);
        assertEquals(1, evaluations.size());
        assertEquals("Annual performance evaluation", evaluations.getFirst().getDescription());
        verify(evaluationRepository, times(1)).findAll();
    }

    @Test
    void getEvaluationByIdReturnsCorrectEvaluation() {
        when(evaluationRepository.findById(1L)).thenReturn(Optional.of(testEvaluation));

        var evaluation = evaluationService.getEvaluationById(1L);

        assertNotNull(evaluation);
        assertEquals("Annual performance evaluation", evaluation.getDescription());
        verify(evaluationRepository, times(1)).findById(1L);
    }

    @Test
    void getEvaluationByIdThrowsNotFoundExceptionForInvalidId() {
        when(evaluationRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> evaluationService.getEvaluationById(999L));
        verify(evaluationRepository, times(1)).findById(999L);
    }

    @Test
    void createEvaluationSavesAndReturnsEvaluation() {
        when(evaluationRepository.save(testEvaluation)).thenReturn(testEvaluation);

        var savedEvaluation = evaluationService.createEvaluation(testEvaluation);

        assertNotNull(savedEvaluation);
        assertEquals("Annual performance evaluation", savedEvaluation.getDescription());
        verify(evaluationRepository, times(1)).save(testEvaluation);
    }

    @Test
    void updateEvaluationUpdatesEvaluationDetails() {
        when(evaluationRepository.findById(1L)).thenReturn(Optional.of(testEvaluation));
        when(evaluationRepository.save(testEvaluation)).thenReturn(testEvaluation);

        testEvaluation.setDescription("Updated evaluation description");
        var updatedEvaluation = evaluationService.updateEvaluation(1L, testEvaluation);

        assertEquals("Updated evaluation description", updatedEvaluation.getDescription());
        verify(evaluationRepository, times(1)).findById(1L);
        verify(evaluationRepository, times(1)).save(testEvaluation);
    }

    @Test
    void updateEvaluationThrowsIllegalArgumentExceptionForNullUpdatedEvaluation() {
        when(evaluationRepository.findById(1L)).thenReturn(Optional.of(testEvaluation));

        assertThrows(IllegalArgumentException.class, () -> evaluationService.updateEvaluation(1L, null));
        verify(evaluationRepository, times(1)).findById(1L);
    }

    @Test
    void updateEvaluationThrowsNotFoundExceptionForInvalidId() {
        when(evaluationRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedEvaluation = new Evaluation();
        updatedEvaluation.setDescription("Invalid update");

        assertThrows(NotFoundException.class, () -> evaluationService.updateEvaluation(999L, updatedEvaluation));
        verify(evaluationRepository, times(1)).findById(999L);
    }

    @Test
    void generatePdfReportThrowsNotFoundExceptionForInvalidId() {
        when(evaluationRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> evaluationService.generatePdfReport(999L));
        verify(evaluationRepository, times(1)).findById(999L);
    }
}