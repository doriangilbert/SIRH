package com.example.sirh_backend.dtos;

public class TrainingRequestDTO {

    private Long id;
    private String status;
    private Long training;
    private Long employee;
    private Long reviewer;

    public TrainingRequestDTO(Long id, String status, Long training, Long employee, Long reviewer) {
        this.id = id;
        this.status = status;
        this.training = training;
        this.employee = employee;
        this.reviewer = reviewer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTraining() {
        return training;
    }

    public void setTraining(Long training) {
        this.training = training;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getReviewer() {
        return reviewer;
    }

    public void setReviewer(Long reviewer) {
        this.reviewer = reviewer;
    }
}
