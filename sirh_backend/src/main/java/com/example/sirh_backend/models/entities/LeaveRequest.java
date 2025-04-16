package com.example.sirh_backend.models.entities;

import com.example.sirh_backend.models.enums.RequestStatus;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class LeaveRequest extends Request {

    private LocalDate startDate;

    private LocalDate endDate;

    public LeaveRequest() {}

    public LeaveRequest(LocalDate startDate, LocalDate endDate, Employee employee) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = RequestStatus.PENDING;
        this.employee = employee;
        this.observers = new ArrayList<>();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", employee=" + employee +
                ", reviewer=" + reviewer +
                '}';
    }
}
