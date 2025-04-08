package com.example.sirh_backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public LeaveRequest() {}

    public LeaveRequest(LocalDate startDate, LocalDate endDate, Employee employee) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = RequestStatus.PENDING;
        this.employee = employee;
    }

    public Long getId() {
        return id;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", employee=" + employee +
                '}';
    }
}
