package com.example.sirh_backend.services;

import com.example.sirh_backend.models.entities.LeaveRequest;
import com.example.sirh_backend.models.enums.RequestStatus;
import com.example.sirh_backend.repositories.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final NotificationService notificationService;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, NotificationService notificationService) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.notificationService = notificationService;
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest getLeaveRequestById(long id) {
        return leaveRequestRepository.findById(id).orElse(null);
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
                leaveRequest.notifyObservers(
                        "A leave request has been approved.\n" +
                        "Details:\n" +
                        "- Leave Request ID: " + leaveRequest.getId() + "\n" +
                        "- Start Date: " + leaveRequest.getStartDate() + "\n" +
                        "- End Date: " + leaveRequest.getEndDate()
                );
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
                leaveRequest.notifyObservers(
                        "A leave request has been refused.\n" +
                        "Details:\n" +
                        "- Leave Request ID: " + leaveRequest.getId() + "\n" +
                        "- Start Date: " + leaveRequest.getStartDate() + "\n" +
                        "- End Date: " + leaveRequest.getEndDate()
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
