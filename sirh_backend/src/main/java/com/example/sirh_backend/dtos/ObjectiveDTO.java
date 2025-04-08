package com.example.sirh_backend.dtos;

public class ObjectiveDTO {

    private Long id;
    private String description;
    private boolean achieved;
    private Long evaluation;

    public ObjectiveDTO(Long id, String description, boolean achieved, Long evaluation) {
        this.id = id;
        this.description = description;
        this.achieved = achieved;
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

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public Long getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Long evaluation) {
        this.evaluation = evaluation;
    }
}
