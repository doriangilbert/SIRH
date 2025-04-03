package com.example.sirh_backend.services;

import com.example.sirh_backend.models.Leave;
import com.example.sirh_backend.repositories.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Leave getLeaveById(long id) {
        return leaveRepository.findById(id).orElse(null);
    }

    public Leave createLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
}
