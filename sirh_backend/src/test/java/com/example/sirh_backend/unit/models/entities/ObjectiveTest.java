package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.entities.Objective;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectiveTest {

    private Objective objective;
    private Evaluation evaluation;

    @BeforeEach
    void setUp() {
        evaluation = new Evaluation();
        objective = new Objective("Test Description", evaluation);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("Test Description", objective.getDescription());
        assertFalse(objective.isAchieved());
        assertEquals(evaluation, objective.getEvaluation());
    }

    @Test
    void getDescriptionReturnsCorrectDescription() {
        objective.setDescription("Test Description");
        assertEquals("Test Description", objective.getDescription());
    }

    @Test
    void setDescriptionUpdatesDescription() {
        objective.setDescription("Updated Description");
        assertEquals("Updated Description", objective.getDescription());
    }

    @Test
    void isAchievedReturnsCorrectValue() {
        objective.setAchieved(true);
        assertTrue(objective.isAchieved());
    }

    @Test
    void setAchievedUpdatesAchieved() {
        objective.setAchieved(false);
        assertFalse(objective.isAchieved());
    }

    @Test
    void getEvaluationReturnsCorrectEvaluation() {
        objective.setEvaluation(evaluation);
        assertEquals(evaluation, objective.getEvaluation());
    }

    @Test
    void setEvaluationUpdatesEvaluation() {
        Evaluation newEvaluation = new Evaluation();
        objective.setEvaluation(newEvaluation);
        assertEquals(newEvaluation, objective.getEvaluation());
    }
}