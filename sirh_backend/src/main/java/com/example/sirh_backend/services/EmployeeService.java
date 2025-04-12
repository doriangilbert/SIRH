package com.example.sirh_backend.services;

import com.example.sirh_backend.models.Employee;
import com.example.sirh_backend.models.LeaveRequest;
import com.example.sirh_backend.models.Training;
import com.example.sirh_backend.models.TrainingRequest;
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

    public EmployeeService(EmployeeRepository employeeRepository, LeaveRequestService leaveRequestService, TrainingService trainingService, TrainingRequestService trainingRequestService) {
        this.employeeRepository = employeeRepository;
        this.leaveRequestService = leaveRequestService;
        this.trainingService = trainingService;
        this.trainingRequestService = trainingRequestService;

    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setLeaveBalance(updatedEmployee.getLeaveBalance());
            employee.setPosition(updatedEmployee.getPosition());
            employee.setSkills(updatedEmployee.getSkills());
            employee.setTeam(updatedEmployee.getTeam());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public LeaveRequest makeLeaveRequest(long employeeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            if (!endDate.isBefore(startDate)) {
                long leaveDuration = startDate.until(endDate).getDays() + 1;
                if (employee.getLeaveBalance() >= leaveDuration) {
                    employee.setLeaveBalance((int) (employee.getLeaveBalance() - leaveDuration));
                    updateEmployee(employeeId, employee);
                    LeaveRequest leaveRequest = new LeaveRequest(startDate, endDate, employee);
                    if (employee.getTeam() != null && employee.getTeam().getManager() != null) {
                        leaveRequest.setReviewer(employee.getTeam().getManager());
                    } else {
                        throw new RuntimeException("No manager found for the employee's team");
                    }
                    return leaveRequestService.createLeaveRequest(leaveRequest);
                } else {
                    throw new RuntimeException("Insufficient leave balance");
                }
            } else {
                throw new IllegalArgumentException("End date must be after start date");
            }
        } else {
            throw new IllegalArgumentException("Employee does not exist");
        }
    }

    public TrainingRequest makeTrainingRequest(long trainingId, long employeeId) {
        Training training = trainingService.getTrainingById(trainingId);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (training != null) {
            if (employee != null) {
                TrainingRequest trainingRequest = new TrainingRequest(training, employee);
                if (employee.getTeam() != null && employee.getTeam().getManager() != null) {
                    trainingRequest.setReviewer(employee.getTeam().getManager());
                } else {
                    throw new RuntimeException("No manager found for the employee's team");
                }
                return trainingRequestService.createTrainingRequest(trainingRequest);
            } else {
                throw new IllegalArgumentException("Employee does not exist");
            }
        } else {
            throw new IllegalArgumentException("Training does not exist");
        }
    }
}
