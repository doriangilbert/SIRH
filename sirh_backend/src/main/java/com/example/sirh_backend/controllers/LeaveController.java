package com.example.sirh_backend.controllers;

import com.example.sirh_backend.models.Leave;
import com.example.sirh_backend.services.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/leaves")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/leaves/{id}")
    public Leave getLeaveById(@PathVariable long id) {
        return leaveService.getLeaveById(id);
    }

    @PostMapping("/leaves")
    public Leave createLeave(@RequestBody Leave leave) {
        return leaveService.createLeave(leave);
    }
}
