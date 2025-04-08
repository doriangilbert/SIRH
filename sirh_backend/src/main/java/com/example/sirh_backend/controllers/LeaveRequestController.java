package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.LeaveRequestDTO;
import com.example.sirh_backend.models.LeaveRequest;
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
        return leaveRequestService.getAllLeaveRequests();
    }

    @GetMapping("/leaverequests/{id}")
    public LeaveRequestDTO getLeaveRequestsById(@PathVariable long id) {
        return leaveRequestService.getLeaveRequestById(id);
    }

    @PostMapping("/leaverequests")
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return leaveRequestService.createLeaveRequest(leaveRequest);
    }
}
