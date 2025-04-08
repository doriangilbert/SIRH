package com.example.sirh_backend.dtos;

public class FeedbackDTO {

    private Long id;
    private String description;
    private Long reviewer;
    private Long evaluation;

    public FeedbackDTO(Long id, String description, Long reviewer, Long evaluation) {
        this.id = id;
        this.description = description;
        this.reviewer = reviewer;
        this.evaluation = evaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getReviewer() {
        return reviewer;
    }

    public void setReviewer(Long reviewer) {
        this.reviewer = reviewer;
    }

    public Long getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Long evaluation) {
        this.evaluation = evaluation;
    }
}
