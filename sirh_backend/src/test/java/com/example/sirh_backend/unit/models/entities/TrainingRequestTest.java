package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Training;
import com.example.sirh_backend.models.entities.TrainingRequest;
import com.example.sirh_backend.models.enums.RequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingRequestTest {

    private Training training;
    private Employee employee;
    private TrainingRequest trainingRequest;

    @BeforeEach
    void setUp() {
        training = new Training();
        employee = new Employee("John", "Doe", null, null);
        trainingRequest = new TrainingRequest(training, employee);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals(RequestStatus.PENDING, trainingRequest.getStatus());
        assertEquals(training, trainingRequest.getTraining());
        assertEquals(employee, trainingRequest.getEmployee());
        assertNotNull(trainingRequest.getObservers());
        assertTrue(trainingRequest.getObservers().isEmpty());
    }

    @Test
    void getStatusReturnsCorrectStatus() {
        trainingRequest.setStatus(RequestStatus.APPROVED);
        assertEquals(RequestStatus.APPROVED, trainingRequest.getStatus());
    }

    @Test
    void setStatusUpdatesStatus() {
        trainingRequest.setStatus(RequestStatus.REFUSED);
        assertEquals(RequestStatus.REFUSED, trainingRequest.getStatus());
    }

    @Test
    void getEmployeeReturnsCorrectEmployee() {
        assertEquals(employee, trainingRequest.getEmployee());
    }

    @Test
    void setEmployeeUpdatesEmployee() {
        Employee newEmployee = new Employee("Alice", "Brown", null, null);
        trainingRequest.setEmployee(newEmployee);
        assertEquals(newEmployee, trainingRequest.getEmployee());
    }

    @Test
    void getReviewerReturnsCorrectReviewer() {
        Employee reviewer = new Employee("Bob", "White", null, null);
        trainingRequest.setReviewer(reviewer);
        assertEquals(reviewer, trainingRequest.getReviewer());
    }

    @Test
    void setReviewerUpdatesReviewer() {
        Employee reviewer = new Employee("Charlie", "Green", null, null);
        trainingRequest.setReviewer(reviewer);
        assertEquals(reviewer, trainingRequest.getReviewer());
    }

    @Test
    void getTrainingReturnsCorrectTraining() {
        assertEquals(training, trainingRequest.getTraining());
    }

    @Test
    void setTrainingUpdatesTraining() {
        Training newTraining = new Training();
        trainingRequest.setTraining(newTraining);
        assertEquals(newTraining, trainingRequest.getTraining());
    }
}