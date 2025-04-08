package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.LeaveRequestDTO;
import com.example.sirh_backend.models.LeaveRequest;
import com.example.sirh_backend.repositories.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream()
                .map(leave -> new LeaveRequestDTO(
                        leave.getId(),
                        leave.getStartDate().toString(),
                        leave.getEndDate().toString(),
                        leave.getStatus().toString(),
                        leave.getEmployee().getId()
                ))
                .toList();
    }

    public LeaveRequestDTO getLeaveRequestById(long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);
        if (leaveRequest != null) {
            return new LeaveRequestDTO(
                    leaveRequest.getId(),
                    leaveRequest.getStartDate().toString(),
                    leaveRequest.getEndDate().toString(),
                    leaveRequest.getStatus().toString(),
                    leaveRequest.getEmployee().getId()
            );
        }
        return null;
    }

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }
}
