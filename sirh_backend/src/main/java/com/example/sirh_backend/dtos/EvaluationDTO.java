package com.example.sirh_backend.dtos;

import java.util.List;

public class EvaluationDTO {

    private Long id;
    private int year;
    private String description;
    private String status;
    private Long employee;
    private List<Long> feedbacks;
    private List<Long> objectives;

    public EvaluationDTO(Long id, int year, String description, String status, Long employee, List<Long> feedbacks, List<Long> objectives) {
        this.id = id;
        this.year = year;
        this.description = description;
        this.status = status;
        this.employee = employee;
        this.feedbacks = feedbacks;
        this.objectives = objectives;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public List<Long> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Long> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Long> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Long> objectives) {
        this.objectives = objectives;
    }
}
