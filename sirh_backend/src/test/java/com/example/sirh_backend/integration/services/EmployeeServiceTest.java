package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.*;
import com.example.sirh_backend.repositories.EmployeeRepository;
import com.example.sirh_backend.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private LeaveRequestService leaveRequestService;

    @Mock
    private TrainingService trainingService;

    @Mock
    private TrainingRequestService trainingRequestService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Employee manager = new Employee();
        manager.setFirstName("Jane");
        manager.setLastName("Smith");

        Team team = new Team();
        team.setName("Development Team");
        team.setManager(manager);

        testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setLeaveBalance(10);
        testEmployee.setTeam(team);
    }

    @Test
    void getAllEmployeesReturnsAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(testEmployee));

        var employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertEquals(1, employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeeByIdReturnsCorrectEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));

        var employee = employeeService.getEmployeeById(1L);

        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeByIdThrowsNotFoundExceptionForInvalidId() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> employeeService.getEmployeeById(999L));
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    void createEmployeeSavesAndReturnsEmployee() {
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

        var savedEmployee = employeeService.createEmployee(testEmployee);

        assertNotNull(savedEmployee);
        assertEquals("John", savedEmployee.getFirstName());
        verify(employeeRepository, times(1)).save(testEmployee);
    }

    @Test
    void updateEmployeeUpdatesEmployeeDetails() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

        testEmployee.setFirstName("UpdatedName");
        var updatedEmployee = employeeService.updateEmployee(1L, testEmployee);

        assertEquals("UpdatedName", updatedEmployee.getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(testEmployee);
    }

    @Test
    void updateEmployeeThrowsIllegalArgumentExceptionForNullUpdatedEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));

        assertThrows(IllegalArgumentException.class, () -> employeeService.updateEmployee(1L, null));
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void updateEmployeeThrowsNotFoundExceptionForInvalidId() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedEmployee = new Employee();
        updatedEmployee.setFirstName("Invalid");

        assertThrows(NotFoundException.class, () -> employeeService.updateEmployee(999L, updatedEmployee));
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    void makeLeaveRequestCreatesLeaveRequest() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(2);

        var leaveRequest = new LeaveRequest(startDate, endDate, testEmployee);
        when(leaveRequestService.createLeaveRequest(any())).thenReturn(leaveRequest);

        var result = employeeService.makeLeaveRequest(1L, startDate, endDate);

        assertNotNull(result);
        assertEquals(startDate, result.getStartDate());
        assertEquals(endDate, result.getEndDate());
        assertEquals(7, testEmployee.getLeaveBalance());
        verify(employeeRepository, times(2)).findById(1L);
        verify(leaveRequestService, times(1)).createLeaveRequest(any());
    }

    @Test
    void makeLeaveRequestThrowsExceptionForInsufficientBalance() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(15);

        assertThrows(IllegalStateException.class, () -> employeeService.makeLeaveRequest(1L, startDate, endDate));
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void makeLeaveRequestThrowsExceptionWhenNoManagerFound() {
        testEmployee.getTeam().setManager(null);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(2);

        assertThrows(IllegalStateException.class, () -> employeeService.makeLeaveRequest(1L, startDate, endDate));
        verify(employeeRepository, times(2)).findById(1L);
    }

    @Test
    void makeLeaveRequestThrowsExceptionForInvalidDates() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        var startDate = LocalDate.now();
        var endDate = startDate.minusDays(1);

        assertThrows(IllegalArgumentException.class, () -> employeeService.makeLeaveRequest(1L, startDate, endDate));
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void makeTrainingRequestCreatesTrainingRequest() {
        var training = new Training();
        training.setName("Java Training");

        when(trainingService.getTrainingById(1L)).thenReturn(training);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));

        var trainingRequest = new TrainingRequest();
        trainingRequest.setTraining(training);
        when(trainingRequestService.createTrainingRequest(any())).thenReturn(trainingRequest);

        var result = employeeService.makeTrainingRequest(1L, 1L);

        assertNotNull(result);
        assertEquals("Java Training", result.getTraining().getName());
        verify(trainingService, times(1)).getTrainingById(1L);
        verify(employeeRepository, times(1)).findById(1L);
        verify(trainingRequestService, times(1)).createTrainingRequest(any());
    }

    @Test
    void makeTrainingRequestThrowsExceptionForInvalidTrainingId() {
        when(trainingService.getTrainingById(999L)).thenThrow(new NotFoundException("Training not found"));

        assertThrows(NotFoundException.class, () -> employeeService.makeTrainingRequest(999L, 1L));
        verify(trainingService, times(1)).getTrainingById(999L);
    }
}