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
                    leaveRequest.getStartDate().toString(),
                    leaveRequest.getEndDate().toString(),
                    leaveRequest.getStatus().toString(),
                    leaveRequest.getEmployee().getId(),
                    leaveRequest.getReviewer().getId()
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
