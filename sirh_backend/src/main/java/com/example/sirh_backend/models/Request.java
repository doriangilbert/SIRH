package com.example.sirh_backend.models;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    protected RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    protected Employee employee;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    protected Employee reviewer;

    public Long getId() {
        return id;
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

    public Employee getReviewer() {
        return reviewer;
    }

    public void setReviewer(Employee reviewer) {
        this.reviewer = reviewer;
    }
}
