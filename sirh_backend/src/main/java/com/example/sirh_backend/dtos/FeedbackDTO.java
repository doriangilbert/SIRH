package com.example.sirh_backend.dtos;

public class FeedbackDTO {

    private Long id;
    private String description;
    private Long reviewerId;
    private Long evaluationId;

    public FeedbackDTO(Long id, String description, Long reviewerId, Long evaluationId) {
        this.id = id;
        this.description = description;
        this.reviewerId = reviewerId;
        this.evaluationId = evaluationId;
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

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }
}
