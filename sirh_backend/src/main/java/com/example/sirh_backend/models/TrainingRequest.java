package com.example.sirh_backend.models;

import jakarta.persistence.*;

@Entity
public class TrainingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Employee reviewer;

    public TrainingRequest() {}

    public TrainingRequest(Training training, Employee employee) {
        this.status = RequestStatus.PENDING;
        this.training = training;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
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

    @Override
    public String toString() {
        return "TrainingRequest{" +
                "id=" + id +
                ", status=" + status +
                ", training=" + training +
                ", employee=" + employee +
                ", reviewer=" + reviewer +
                '}';
    }
}
