package com.example.sirh_backend.dtos;

public class ObjectiveDTO {

    private Long id;
    private String description;
    private boolean achieved;
    private Long evaluationId;

    public ObjectiveDTO(Long id, String description, boolean achieved, Long evaluationId) {
        this.id = id;
        this.description = description;
        this.achieved = achieved;
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

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }
}
