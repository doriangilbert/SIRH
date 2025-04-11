package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.EmployeeDTO;
import com.example.sirh_backend.mappers.EmployeeMapper;
import com.example.sirh_backend.models.Employee;
import com.example.sirh_backend.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return EmployeeMapper.toDTO(employeeService.getAllEmployees());
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable long id) {
        return EmployeeMapper.toDTO(employeeService.getEmployeeById(id));
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
}
