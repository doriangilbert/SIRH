package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.EmployeeDTO;
import com.example.sirh_backend.dtos.LeaveRequestDTO;
import com.example.sirh_backend.dtos.TrainingRequestDTO;
import com.example.sirh_backend.mappers.EmployeeMapper;
import com.example.sirh_backend.mappers.LeaveRequestMapper;
import com.example.sirh_backend.mappers.TrainingMapper;
import com.example.sirh_backend.mappers.TrainingRequestMapper;
import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.LeaveRequest;
import com.example.sirh_backend.models.entities.TrainingRequest;
import com.example.sirh_backend.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(EmployeeMapper.toDTO(employeeService.getAllEmployees()), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id) {
        return new ResponseEntity<>(EmployeeMapper.toDTO(employeeService.getEmployeeById(id)), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(EmployeeMapper.toDTO(employeeService.createEmployee(employee)), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(EmployeeMapper.toDTO(employeeService.updateEmployee(id, employee)), HttpStatus.OK);
    }

    @PostMapping("/employees/{id}/leaverequests")
    public ResponseEntity<LeaveRequestDTO> makeLeaveRequest(@PathVariable long id, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return new ResponseEntity<>(LeaveRequestMapper.toDTO(employeeService.makeLeaveRequest(id, LocalDate.parse(startDate), LocalDate.parse(endDate))), HttpStatus.CREATED);
    }

    @PostMapping("/employees/{id}/trainingrequests")
    public ResponseEntity<TrainingRequestDTO> makeTrainingRequest(@PathVariable long id, @RequestParam("trainingId") long trainingId) {
        return new ResponseEntity<>(TrainingRequestMapper.toDTO(employeeService.makeTrainingRequest(trainingId, id)), HttpStatus.CREATED);
    }
}
