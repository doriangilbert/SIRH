package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.entities.Feedback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedbackTest {

    private Employee reviewer;
    private Evaluation evaluation;
    private Feedback feedback;

    @BeforeEach
    void setUp() {
        reviewer = new Employee("John", "Doe", null, null);
        evaluation = new Evaluation();
        feedback = new Feedback("Great work", reviewer, evaluation);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("Great work", feedback.getDescription());
        assertEquals(reviewer, feedback.getReviewer());
        assertEquals(evaluation, feedback.getEvaluation());
    }

    @Test
    void getDescriptionReturnsCorrectDescription() {
        feedback.setDescription("Excellent performance");
        assertEquals("Excellent performance", feedback.getDescription());
    }

    @Test
    void setDescriptionUpdatesDescription() {
        feedback.setDescription("Needs improvement");
        assertEquals("Needs improvement", feedback.getDescription());
    }

    @Test
    void getReviewerReturnsCorrectReviewer() {
        feedback.setReviewer(reviewer);
        assertEquals(reviewer, feedback.getReviewer());
    }

    @Test
    void setReviewerUpdatesReviewer() {
        Employee newReviewer = new Employee("Jane", "Smith", null, null);
        feedback.setReviewer(newReviewer);
        assertEquals(newReviewer, feedback.getReviewer());
    }

    @Test
    void getEvaluationReturnsCorrectEvaluation() {
        feedback.setEvaluation(evaluation);
        assertEquals(evaluation, feedback.getEvaluation());
    }

    @Test
    void setEvaluationUpdatesEvaluation() {
        Evaluation newEvaluation = new Evaluation();
        feedback.setEvaluation(newEvaluation);
        assertEquals(newEvaluation, feedback.getEvaluation());
    }
}