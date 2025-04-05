package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.LeaveDTO;
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

    public List<LeaveDTO> getAllLeaves() {
        return leaveRepository.findAll().stream()
                .map(leave -> new LeaveDTO(
                        leave.getId(),
                        leave.getStartDate().toString(),
                        leave.getEndDate().toString(),
                        leave.getStatus().toString(),
                        leave.getEmployee().getId()
                ))
                .toList();
    }

    public LeaveDTO getLeaveById(long id) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            return new LeaveDTO(
                    leave.getId(),
                    leave.getStartDate().toString(),
                    leave.getEndDate().toString(),
                    leave.getStatus().toString(),
                    leave.getEmployee().getId()
            );
        }
        return null;
    }

    public Leave createLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
}
