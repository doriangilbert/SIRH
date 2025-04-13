package com.example.sirh_backend.models;

import jakarta.persistence.*;

@Entity
public class TrainingRequest extends Request {

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    public TrainingRequest() {}

    public TrainingRequest(Training training, Employee employee) {
        this.status = RequestStatus.PENDING;
        this.training = training;
        this.employee = employee;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
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
