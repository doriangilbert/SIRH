package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.EmployeeDTO;
import com.example.sirh_backend.mappers.EmployeeMapper;
import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.LeaveRequest;
import com.example.sirh_backend.models.entities.TrainingRequest;
import com.example.sirh_backend.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/employees/{id}/leaverequests")
    public LeaveRequest makeLeaveRequest(@PathVariable long id, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return employeeService.makeLeaveRequest(id, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @PostMapping("/employees/{id}/trainingrequests")
    public TrainingRequest makeTrainingRequest(@PathVariable long id, @RequestParam("trainingId") long trainingId) {
        return employeeService.makeTrainingRequest(trainingId, id);
    }
}
