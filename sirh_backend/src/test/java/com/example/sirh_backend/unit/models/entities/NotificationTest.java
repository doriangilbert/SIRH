package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationTest {

    private Notification notification;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John", "Doe", null, null);
        notification = new Notification("Title", "Description", employee);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("Title", notification.getTitle());
        assertEquals("Description", notification.getDescription());
        assertEquals(employee, notification.getEmployee());
    }

    @Test
    void getTitleReturnsCorrectTitle() {
        notification.setTitle("Test Title");
        assertEquals("Test Title", notification.getTitle());
    }

    @Test
    void setTitleUpdatesTitle() {
        notification.setTitle("Updated Title");
        assertEquals("Updated Title", notification.getTitle());
    }

    @Test
    void getDescriptionReturnsCorrectDescription() {
        notification.setDescription("Test Description");
        assertEquals("Test Description", notification.getDescription());
    }

    @Test
    void setDescriptionUpdatesDescription() {
        notification.setDescription("Updated Description");
        assertEquals("Updated Description", notification.getDescription());
    }

    @Test
    void getEmployeeReturnsCorrectEmployee() {
        notification.setEmployee(employee);
        assertEquals(employee, notification.getEmployee());
    }

    @Test
    void setEmployeeUpdatesEmployee() {
        Employee newEmployee = new Employee("Jane", "Smith", null, null);
        notification.setEmployee(newEmployee);
        assertEquals(newEmployee, notification.getEmployee());
    }
}