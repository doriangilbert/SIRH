package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.LeaveRequestDTO;
import com.example.sirh_backend.models.entities.LeaveRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeaveRequestMapper {

    public static LeaveRequestDTO toDTO(LeaveRequest leaveRequest) {
        if (leaveRequest != null) {
            return new LeaveRequestDTO(
                    leaveRequest.getId(),
                    leaveRequest.getStartDate() != null ? leaveRequest.getStartDate().toString() : null,
                    leaveRequest.getEndDate() != null ? leaveRequest.getEndDate().toString() : null,
                    leaveRequest.getStatus() != null ? leaveRequest.getStatus().toString() : null,
                    leaveRequest.getEmployee() != null ? leaveRequest.getEmployee().getId() : null,
                    leaveRequest.getReviewer() != null ? leaveRequest.getReviewer().getId() : null
            );
        }
        return null;
    }

    public static List<LeaveRequestDTO> toDTO(List<LeaveRequest> leaveRequests) {
        return leaveRequests.stream()
                .map(LeaveRequestMapper::toDTO)
                .collect(Collectors.toList());
    }
}
