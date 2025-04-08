package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.EmployeeDTO;
import com.example.sirh_backend.models.Employee;
import com.example.sirh_backend.models.LeaveRequest;
import com.example.sirh_backend.models.Skill;
import com.example.sirh_backend.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getLeaveBalance(),
                        employee.getLeaveRequests().stream().map(LeaveRequest::getId).collect(Collectors.toList()),
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
                    employee.getLeaveRequests().stream().map(LeaveRequest::getId).collect(Collectors.toList()),
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
}
