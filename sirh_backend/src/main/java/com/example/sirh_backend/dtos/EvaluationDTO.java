package com.example.sirh_backend.dtos;

import java.util.List;

public class EvaluationDTO {

    private Long id;
    private int year;
    private String description;
    private String status;
    private Long employeeId;
    private List<Long> feedbacksIds;
    private List<Long> objectivesIds;

    public EvaluationDTO(Long id, int year, String description, String status, Long employeeId, List<Long> feedbacksIds, List<Long> objectivesIds) {
        this.id = id;
        this.year = year;
        this.description = description;
        this.status = status;
        this.employeeId = employeeId;
        this.feedbacksIds = feedbacksIds;
        this.objectivesIds = objectivesIds;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<Long> getFeedbacksIds() {
        return feedbacksIds;
    }

    public void setFeedbacksIds(List<Long> feedbacksIds) {
        this.feedbacksIds = feedbacksIds;
    }

    public List<Long> getObjectivesIds() {
        return objectivesIds;
    }

    public void setObjectivesIds(List<Long> objectivesIds) {
        this.objectivesIds = objectivesIds;
    }
}
