package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.LeaveRequest;
import com.example.sirh_backend.models.enums.RequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeaveRequestTest {

    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveRequest leaveRequest;

    @BeforeEach
    void setUp() {
        employee = new Employee("John", "Doe", null, null);
        startDate = LocalDate.of(2025, 3, 26);
        endDate = LocalDate.of(2025, 4, 3);
        leaveRequest = new LeaveRequest(startDate, endDate, employee);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals(startDate, leaveRequest.getStartDate());
        assertEquals(endDate, leaveRequest.getEndDate());
        assertEquals(RequestStatus.PENDING, leaveRequest.getStatus());
        assertEquals(employee, leaveRequest.getEmployee());
        assertNotNull(leaveRequest.getObservers());
    }

    @Test
    void constructorThrowsExceptionWhenStartDateAfterEndDate() {
        LocalDate invalidStartDate = LocalDate.of(2025, 4, 3);
        LocalDate invalidEndDate = LocalDate.of(2025, 3, 26);

        assertThrows(IllegalArgumentException.class, () -> new LeaveRequest(invalidStartDate, invalidEndDate, employee));
    }

    @Test
    void getStatusReturnsCorrectStatus() {
        leaveRequest.setStatus(RequestStatus.APPROVED);
        assertEquals(RequestStatus.APPROVED, leaveRequest.getStatus());
    }

    @Test
    void setStatusUpdatesStatus() {
        leaveRequest.setStatus(RequestStatus.REFUSED);
        assertEquals(RequestStatus.REFUSED, leaveRequest.getStatus());
    }

    @Test
    void getEmployeeReturnsCorrectEmployee() {
        assertEquals(employee, leaveRequest.getEmployee());
    }

    @Test
    void setEmployeeUpdatesEmployee() {
        Employee newEmployee = new Employee("Jane", "Smith", null, null);
        leaveRequest.setEmployee(newEmployee);
        assertEquals(newEmployee, leaveRequest.getEmployee());
    }

    @Test
    void getReviewerReturnsCorrectReviewer() {
        Employee reviewer = new Employee("John", "Doe", null, null);
        leaveRequest.setReviewer(reviewer);
        assertEquals(reviewer, leaveRequest.getReviewer());
    }

    @Test
    void setReviewerUpdatesReviewer() {
        Employee reviewer = new Employee("Jane", "Smith", null, null);
        leaveRequest.setReviewer(reviewer);
        assertEquals(reviewer, leaveRequest.getReviewer());
    }

    @Test
    void getStartDateReturnsCorrectStartDate() {
        assertEquals(startDate, leaveRequest.getStartDate());
    }

    @Test
    void setStartDateUpdatesStartDate() {
        leaveRequest.setStartDate(startDate);
        assertEquals(startDate, leaveRequest.getStartDate());
    }

    @Test
    void setStartDateThrowsExceptionWhenStartDateAfterEndDate() {
        LocalDate invalidStartDate = LocalDate.of(2025, 4, 10);
        assertThrows(IllegalArgumentException.class, () -> leaveRequest.setStartDate(invalidStartDate));
    }

    @Test
    void getEndDateReturnsCorrectEndDate() {
        assertEquals(endDate, leaveRequest.getEndDate());
    }

    @Test
    void setEndDateUpdatesEndDate() {
        leaveRequest.setEndDate(endDate);
        assertEquals(endDate, leaveRequest.getEndDate());
    }

    @Test
    void setEndDateThrowsExceptionWhenEndDateBeforeStartDate() {
        LocalDate invalidEndDate = LocalDate.of(2025, 3, 20);
        assertThrows(IllegalArgumentException.class, () -> leaveRequest.setEndDate(invalidEndDate));
    }
}