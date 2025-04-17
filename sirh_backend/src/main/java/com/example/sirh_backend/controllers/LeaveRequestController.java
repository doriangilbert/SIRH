package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.LeaveRequestDTO;
import com.example.sirh_backend.mappers.LeaveRequestMapper;
import com.example.sirh_backend.models.entities.LeaveRequest;
import com.example.sirh_backend.services.LeaveRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping("/leaverequests")
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaveRequests() {
        return new ResponseEntity<>(LeaveRequestMapper.toDTO(leaveRequestService.getAllLeaveRequests()), HttpStatus.OK);
    }

    @GetMapping("/leaverequests/{id}")
    public ResponseEntity<LeaveRequestDTO> getLeaveRequestsById(@PathVariable long id) {
        return new ResponseEntity<>(LeaveRequestMapper.toDTO(leaveRequestService.getLeaveRequestById(id)), HttpStatus.OK);
    }

    @PostMapping("/leaverequests")
    public ResponseEntity<LeaveRequestDTO> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return new ResponseEntity<>(LeaveRequestMapper.toDTO(leaveRequestService.createLeaveRequest(leaveRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/leaverequests/{id}")
    public ResponseEntity<LeaveRequestDTO> updateLeaveRequest(@PathVariable long id, @RequestBody LeaveRequest leaveRequest) {
        return new ResponseEntity<>(LeaveRequestMapper.toDTO(leaveRequestService.updateLeaveRequest(id, leaveRequest)), HttpStatus.OK);
    }

    @PutMapping("/leaverequests/{id}/approve")
    public ResponseEntity<LeaveRequestDTO> approveLeaveRequest(@PathVariable long id, @RequestBody String requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            long reviewerId = jsonNode.get("reviewerId").asLong();
            return new ResponseEntity<>(LeaveRequestMapper.toDTO(leaveRequestService.approveLeaveRequest(id, reviewerId)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/leaverequests/{id}/refuse")
    public ResponseEntity<LeaveRequestDTO> refuseLeaveRequest(@PathVariable long id, @RequestBody String requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            long reviewerId = jsonNode.get("reviewerId").asLong();
            return new ResponseEntity<>(LeaveRequestMapper.toDTO(leaveRequestService.refuseLeaveRequest(id, reviewerId)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
