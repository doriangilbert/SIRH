package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.LeaveRequestDTO;
import com.example.sirh_backend.models.LeaveRequest;
import com.example.sirh_backend.models.RequestStatus;
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
                .map(leaveRequest -> new LeaveRequestDTO(
                        leaveRequest.getId(),
                        leaveRequest.getStartDate().toString(),
                        leaveRequest.getEndDate().toString(),
                        leaveRequest.getStatus().toString(),
                        leaveRequest.getEmployee().getId(),
                        leaveRequest.getReviewer().getId()
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
                    leaveRequest.getEmployee().getId(),
                    leaveRequest.getReviewer().getId()
            );
        }
        return null;
    }

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest updateLeaveRequest(long id, LeaveRequest updatedLeaveRequest) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);
        if (leaveRequest != null) {
            leaveRequest.setStartDate(updatedLeaveRequest.getStartDate());
            leaveRequest.setEndDate(updatedLeaveRequest.getEndDate());
            leaveRequest.setStatus(updatedLeaveRequest.getStatus());
            leaveRequest.setEmployee(updatedLeaveRequest.getEmployee());
            leaveRequest.setReviewer(updatedLeaveRequest.getReviewer());
            return leaveRequestRepository.save(leaveRequest);
        }
        return null;
    }

    public LeaveRequest approveLeaveRequest(long id, long reviewerId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);
        if (leaveRequest != null) {
            if (leaveRequest.getReviewer().getId() == reviewerId) {
                leaveRequest.setStatus(RequestStatus.APPROVED);
                return updateLeaveRequest(id, leaveRequest);
            } else {
                throw new IllegalArgumentException("Employee is not the reviewer");
            }
        } else {
            throw new IllegalArgumentException("Leave request does not exist");
        }
    }

    public LeaveRequest refuseLeaveRequest(long id, long reviewerId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);
        if (leaveRequest != null) {
            if (leaveRequest.getReviewer().getId() == reviewerId) {
                leaveRequest.setStatus(RequestStatus.REFUSED);
                long leaveDuration = leaveRequest.getStartDate().until(leaveRequest.getEndDate()).getDays() + 1;
                leaveRequest.getEmployee().setLeaveBalance(
                        leaveRequest.getEmployee().getLeaveBalance() + (int) leaveDuration
                );
                return updateLeaveRequest(id, leaveRequest);
            } else {
                throw new IllegalArgumentException("Employee is not the reviewer");
            }
        } else {
            throw new IllegalArgumentException("Leave request does not exist");
        }
    }
}
