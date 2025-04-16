package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Team team;
    private Employee manager;

    @BeforeEach
    void setUp() {
        manager = new Employee("Default", "Manager", null, null);
        team = new Team("Default Team", manager);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("Default Team", team.getName());
        assertEquals(manager, team.getManager());
        assertNotNull(team.getEmployees());
        assertTrue(team.getEmployees().isEmpty());
    }

    @Test
    void getNameReturnsCorrectName() {
        team.setName("HR");
        assertEquals("HR", team.getName());
    }

    @Test
    void setNameUpdatesName() {
        team.setName("Finance");
        assertEquals("Finance", team.getName());
    }

    @Test
    void getEmployeesReturnsCorrectEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jane", "Smith", null, null));
        team.setEmployees(employees);
        assertEquals(employees, team.getEmployees());
    }

    @Test
    void setEmployeesUpdatesEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "Brown", null, null));
        team.setEmployees(employees);
        assertEquals(employees, team.getEmployees());
    }

    @Test
    void getManagerReturnsCorrectManager() {
        assertEquals(manager, team.getManager());
    }

    @Test
    void setManagerUpdatesManager() {
        Employee newManager = new Employee("Charlie", "Green", null, null);
        team.setManager(newManager);
        assertEquals(newManager, team.getManager());
    }

    @Test
    void addEmployeeAddsEmployeeToList() {
        Employee employee = new Employee("David", "Black", null, null);
        team.addEmployee(employee);
        assertTrue(team.getEmployees().contains(employee));
    }

    @Test
    void addEmployeeThrowsExceptionIfEmployeeAlreadyExists() {
        Employee employee = new Employee("Eve", "Gray", null, null);
        team.addEmployee(employee);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> team.addEmployee(employee));
        assertEquals("Employee already exists", exception.getMessage());
    }

    @Test
    void removeEmployeeRemovesEmployeeFromList() {
        Employee employee = new Employee("Frank", "Blue", null, null);
        team.addEmployee(employee);
        team.removeEmployee(employee);
        assertFalse(team.getEmployees().contains(employee));
    }

    @Test
    void removeEmployeeThrowsExceptionIfEmployeeDoesNotExist() {
        Employee employee = new Employee("Grace", "Yellow", null, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> team.removeEmployee(employee));
        assertEquals("Employee does not exist", exception.getMessage());
    }
}