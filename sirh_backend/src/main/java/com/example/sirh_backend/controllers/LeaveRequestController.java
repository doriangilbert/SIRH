package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.LeaveRequestDTO;
import com.example.sirh_backend.mappers.LeaveRequestMapper;
import com.example.sirh_backend.models.entities.LeaveRequest;
import com.example.sirh_backend.services.LeaveRequestService;
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
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return LeaveRequestMapper.toDTO(leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/leaverequests/{id}")
    public LeaveRequestDTO getLeaveRequestsById(@PathVariable long id) {
        return LeaveRequestMapper.toDTO(leaveRequestService.getLeaveRequestById(id));
    }

    @PostMapping("/leaverequests")
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return leaveRequestService.createLeaveRequest(leaveRequest);
    }

    @PutMapping("/leaverequests/{id}")
    public LeaveRequest updateLeaveRequest(@PathVariable long id, @RequestBody LeaveRequest leaveRequest) {
        return leaveRequestService.updateLeaveRequest(id, leaveRequest);
    }

    @PutMapping("/leaverequests/{id}/approve")
    public LeaveRequest approveLeaveRequest(@PathVariable long id, @RequestParam(name = "reviewerId") long reviewerId) {
        return leaveRequestService.approveLeaveRequest(id, reviewerId);
    }

    @PutMapping("/leaverequests/{id}/refuse")
    public LeaveRequest refuseLeaveRequest(@PathVariable long id, @RequestParam(name = "reviewerId") long reviewerId) {
        return leaveRequestService.refuseLeaveRequest(id, reviewerId);
    }
}
