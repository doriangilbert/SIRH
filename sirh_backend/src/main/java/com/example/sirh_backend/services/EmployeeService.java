package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.EmployeeDTO;
import com.example.sirh_backend.models.Employee;
import com.example.sirh_backend.models.LeaveRequest;
import com.example.sirh_backend.models.Skill;
import com.example.sirh_backend.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LeaveRequestService leaveRequestService;

    public EmployeeService(EmployeeRepository employeeRepository, LeaveRequestService leaveRequestService) {
        this.employeeRepository = employeeRepository;
        this.leaveRequestService = leaveRequestService;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getLeaveBalance(),
                        employee.getPosition().getId(),
                        employee.getSkills().stream().map(Skill::getId).collect(Collectors.toList()),
                        employee.getTeam().getId()
                ))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            return new EmployeeDTO(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getLeaveBalance(),
                    employee.getPosition().getId(),
                    employee.getSkills().stream().map(Skill::getId).collect(Collectors.toList()),
                    employee.getTeam().getId()
            );
        }
        return null;
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
}
