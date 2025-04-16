package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.entities.Feedback;
import com.example.sirh_backend.models.entities.Objective;
import com.example.sirh_backend.models.enums.EvaluationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationTest {

    private Evaluation evaluation;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John", "Doe", null, null);
        evaluation = new Evaluation(Year.of(2025), "Annual Review", employee);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals(Year.of(2025), evaluation.getYear());
        assertEquals("Annual Review", evaluation.getDescription());
        assertEquals(EvaluationStatus.WAITING, evaluation.getStatus());
        assertEquals(employee, evaluation.getEmployee());
        assertNotNull(evaluation.getFeedbacks());
        assertNotNull(evaluation.getObjectives());
    }

    @Test
    void getYearReturnsCorrectYear() {
        evaluation.setYear(Year.of(2025));
        assertEquals(Year.of(2025), evaluation.getYear());
    }

    @Test
    void setYearUpdatesYear() {
        evaluation.setYear(Year.of(2024));
        assertEquals(Year.of(2024), evaluation.getYear());
    }

    @Test
    void getDescriptionReturnsCorrectDescription() {
        evaluation.setDescription("Performance Review");
        assertEquals("Performance Review", evaluation.getDescription());
    }

    @Test
    void setDescriptionUpdatesDescription() {
        evaluation.setDescription("Updated Review");
        assertEquals("Updated Review", evaluation.getDescription());
    }

    @Test
    void getStatusReturnsCorrectStatus() {
        evaluation.setStatus(EvaluationStatus.FINISHED);
        assertEquals(EvaluationStatus.FINISHED, evaluation.getStatus());
    }

    @Test
    void setStatusUpdatesStatus() {
        evaluation.setStatus(EvaluationStatus.ONGOING);
        assertEquals(EvaluationStatus.ONGOING, evaluation.getStatus());
    }

    @Test
    void getEmployeeReturnsCorrectEmployee() {
        assertEquals(employee, evaluation.getEmployee());
    }

    @Test
    void setEmployeeUpdatesEmployee() {
        Employee newEmployee = new Employee("Jane", "Smith", null, null);
        evaluation.setEmployee(newEmployee);
        assertEquals(newEmployee, evaluation.getEmployee());
    }

    @Test
    void setFeedbacksUpdatesFeedbacksList() {
        Feedback feedback1 = new Feedback("Feedback 1", null, evaluation);
        Feedback feedback2 = new Feedback("Feedback 2", null, evaluation);
        evaluation.setFeedbacks(List.of(feedback1, feedback2));
        assertEquals(2, evaluation.getFeedbacks().size());
        assertTrue(evaluation.getFeedbacks().contains(feedback1));
        assertTrue(evaluation.getFeedbacks().contains(feedback2));
    }

    @Test
    void setObjectivesUpdatesObjectivesList() {
        Objective objective1 = new Objective("Objective 1", evaluation);
        Objective objective2 = new Objective("Objective 2", evaluation);
        evaluation.setObjectives(List.of(objective1, objective2));
        assertEquals(2, evaluation.getObjectives().size());
        assertTrue(evaluation.getObjectives().contains(objective1));
        assertTrue(evaluation.getObjectives().contains(objective2));
    }

    @Test
    void addFeedbackAddsFeedbackWhenNotAlreadyPresent() {
        Feedback feedback = new Feedback("Good work", null, evaluation);
        evaluation.addFeedback(feedback);
        assertTrue(evaluation.getFeedbacks().contains(feedback));
    }

    @Test
    void addFeedbackThrowsExceptionWhenFeedbackAlreadyExists() {
        Feedback feedback = new Feedback("Good work", null, evaluation);
        evaluation.addFeedback(feedback);
        assertThrows(IllegalArgumentException.class, () -> evaluation.addFeedback(feedback));
    }

    @Test
    void removeFeedbackRemovesFeedbackWhenPresent() {
        Feedback feedback = new Feedback("Good work", null, evaluation);
        evaluation.addFeedback(feedback);
        evaluation.removeFeedback(feedback);
        assertFalse(evaluation.getFeedbacks().contains(feedback));
    }

    @Test
    void removeFeedbackThrowsExceptionWhenFeedbackNotPresent() {
        Feedback feedback = new Feedback("Good work", null, evaluation);
        assertThrows(IllegalArgumentException.class, () -> evaluation.removeFeedback(feedback));
    }

    @Test
    void addObjectiveAddsObjectiveWhenNotAlreadyPresent() {
        Objective objective = new Objective("Improve skills", evaluation);
        evaluation.addObjective(objective);
        assertTrue(evaluation.getObjectives().contains(objective));
    }

    @Test
    void addObjectiveThrowsExceptionWhenObjectiveAlreadyExists() {
        Objective objective = new Objective("Improve skills", evaluation);
        evaluation.addObjective(objective);
        assertThrows(IllegalArgumentException.class, () -> evaluation.addObjective(objective));
    }

    @Test
    void removeObjectiveRemovesObjectiveWhenPresent() {
        Objective objective = new Objective("Improve skills", evaluation);
        evaluation.addObjective(objective);
        evaluation.removeObjective(objective);
        assertFalse(evaluation.getObjectives().contains(objective));
    }

    @Test
    void removeObjectiveThrowsExceptionWhenObjectiveNotPresent() {
        Objective objective = new Objective("Improve skills", evaluation);
        assertThrows(IllegalArgumentException.class, () -> evaluation.removeObjective(objective));
    }
}