package com.example.sirh_backend.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.*;
import com.example.sirh_backend.models.patterns.PriorityNotificationDecorator;
import com.example.sirh_backend.models.patterns.RequestFactory;
import com.example.sirh_backend.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LeaveRequestService leaveRequestService;
    private final TrainingService trainingService;
    private final TrainingRequestService trainingRequestService;
    private final NotificationService notificationService;

    public EmployeeService(EmployeeRepository employeeRepository, LeaveRequestService leaveRequestService, TrainingService trainingService, TrainingRequestService trainingRequestService, NotificationService notificationService) {
        this.employeeRepository = employeeRepository;
        this.leaveRequestService = leaveRequestService;
        this.trainingService = trainingService;
        this.trainingRequestService = trainingRequestService;
        this.notificationService = notificationService;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
        if (updatedEmployee == null) {
            throw new IllegalArgumentException("Updated employee data cannot be null");
        }
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setLeaveBalance(updatedEmployee.getLeaveBalance());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setSkills(updatedEmployee.getSkills());
        employee.setTeam(updatedEmployee.getTeam());
        employee.setNotifications(updatedEmployee.getNotifications());
        return employeeRepository.save(employee);
    }

    public LeaveRequest makeLeaveRequest(long employeeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"));
        if (!endDate.isBefore(startDate)) {
            long leaveDuration = (long) startDate.until(endDate).getDays() + 1;
            if (employee.getLeaveBalance() >= leaveDuration) {
                employee.setLeaveBalance((int) (employee.getLeaveBalance() - leaveDuration));
                updateEmployee(employeeId, employee);
                LeaveRequest leaveRequest = (LeaveRequest) RequestFactory.createRequest("LeaveRequest", startDate, endDate, employee);
                leaveRequest.addObserver(employee);
                if (employee.getTeam() != null && employee.getTeam().getManager() != null) {
                    leaveRequest.setReviewer(employee.getTeam().getManager());
                    leaveRequest.addObserver(employee.getTeam().getManager());
                    PriorityNotificationDecorator priorityNotification = new PriorityNotificationDecorator(
                            new Notification(
                                    "New leave request",
                                    "You have a new leave request to review.\n" +
                                            "Details:\n" +
                                            "- Leave Request ID: " + leaveRequest.getId() + "\n" +
                                            "- Employee ID: " + employee.getId() + "\n" +
                                            "- Start Date: " + startDate + "\n" +
                                            "- End Date: " + endDate,
                                    employee.getTeam().getManager()));
                    Notification notification = new Notification(
                            priorityNotification.getTitle(),
                            priorityNotification.getDescription(),
                            priorityNotification.getEmployee());
                    notificationService.createNotification(notification);
                } else {
                    throw new IllegalStateException("No manager found for the employee's team");
                }
                return leaveRequestService.createLeaveRequest(leaveRequest);
            } else {
                throw new IllegalStateException("Insufficient leave balance");
            }
        } else {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }

    public TrainingRequest makeTrainingRequest(long trainingId, long employeeId) {
        Training training = trainingService.getTrainingById(trainingId);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"));
        TrainingRequest trainingRequest = (TrainingRequest) RequestFactory.createRequest("TrainingRequest", training, employee);
        trainingRequest.addObserver(employee);
        if (employee.getTeam() != null && employee.getTeam().getManager() != null) {
            trainingRequest.setReviewer(employee.getTeam().getManager());
            trainingRequest.addObserver(employee.getTeam().getManager());
            PriorityNotificationDecorator priorityNotification = new PriorityNotificationDecorator(
                    new Notification(
                            "New training request",
                            "You have a new training request to review.\n" +
                                    "Details:\n" +
                                    "- Training Request ID: " + trainingRequest.getId() + "\n" +
                                    "- Employee ID: " + employee.getId() + "\n" +
                                    "- Training ID: " + training.getId() + "\n" +
                                    "- Training Name: " + training.getName(),
                            employee.getTeam().getManager()));
            Notification notification = new Notification(
                    priorityNotification.getTitle(),
                    priorityNotification.getDescription(),
                    priorityNotification.getEmployee());
            notificationService.createNotification(notification);
        } else {
            throw new IllegalStateException("No manager found for the employee's team");
        }
        return trainingRequestService.createTrainingRequest(trainingRequest);
    }
}
