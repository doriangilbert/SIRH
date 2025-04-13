package com.example.sirh_backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LeaveRequest extends Request {

    private LocalDate startDate;

    private LocalDate endDate;

    public LeaveRequest() {}

    public LeaveRequest(LocalDate startDate, LocalDate endDate, Employee employee) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = RequestStatus.PENDING;
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
