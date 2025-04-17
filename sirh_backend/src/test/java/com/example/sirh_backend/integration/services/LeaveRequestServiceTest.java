package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.LeaveRequest;
import com.example.sirh_backend.models.enums.RequestStatus;
import com.example.sirh_backend.repositories.LeaveRequestRepository;
import com.example.sirh_backend.services.LeaveRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaveRequestServiceTest {

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @InjectMocks
    private LeaveRequestService leaveRequestService;

    private LeaveRequest testLeaveRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        testLeaveRequest = new LeaveRequest(LocalDate.now(), LocalDate.now().plusDays(5), employee);
    }

    @Test
    void getAllLeaveRequestsReturnsAllRequests() {
        when(leaveRequestRepository.findAll()).thenReturn(List.of(testLeaveRequest));

        var leaveRequests = leaveRequestService.getAllLeaveRequests();

        assertNotNull(leaveRequests);
        assertEquals(1, leaveRequests.size());
        verify(leaveRequestRepository, times(1)).findAll();
    }

    @Test
    void getLeaveRequestByIdReturnsCorrectRequest() {
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(testLeaveRequest));

        var leaveRequest = leaveRequestService.getLeaveRequestById(1L);

        assertNotNull(leaveRequest);
        verify(leaveRequestRepository, times(1)).findById(1L);
    }

    @Test
    void getLeaveRequestByIdThrowsNotFoundExceptionForInvalidId() {
        when(leaveRequestRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> leaveRequestService.getLeaveRequestById(999L));
        verify(leaveRequestRepository, times(1)).findById(999L);
    }

    @Test
    void createLeaveRequestSavesAndReturnsRequest() {
        when(leaveRequestRepository.save(testLeaveRequest)).thenReturn(testLeaveRequest);

        var savedRequest = leaveRequestService.createLeaveRequest(testLeaveRequest);

        assertNotNull(savedRequest);
        verify(leaveRequestRepository, times(1)).save(testLeaveRequest);
    }

    @Test
    void updateLeaveRequestUpdatesRequestDetails() {
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(testLeaveRequest));
        when(leaveRequestRepository.save(testLeaveRequest)).thenReturn(testLeaveRequest);

        testLeaveRequest.setStatus(RequestStatus.APPROVED);
        var updatedRequest = leaveRequestService.updateLeaveRequest(1L, testLeaveRequest);

        assertEquals(RequestStatus.APPROVED, updatedRequest.getStatus());
        verify(leaveRequestRepository, times(1)).findById(1L);
        verify(leaveRequestRepository, times(1)).save(testLeaveRequest);
    }

    @Test
    void updateLeaveRequestThrowsIllegalArgumentExceptionForNullUpdatedRequest() {
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(testLeaveRequest));

        assertThrows(IllegalArgumentException.class, () -> leaveRequestService.updateLeaveRequest(1L, null));
        verify(leaveRequestRepository, times(1)).findById(1L);
    }
}