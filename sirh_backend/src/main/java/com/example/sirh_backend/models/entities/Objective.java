package com.example.sirh_backend.models.entities;

import jakarta.persistence.*;

@Entity
public class Objective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private boolean achieved;

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    public Objective() {}

    public Objective(String description, Evaluation evaluation) {
        this.description = description;
        this.achieved = false;
        this.evaluation = evaluation;
    }

    public Long getId() {
        return id;
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

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "Objective{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", achieved=" + achieved +
                ", evaluation=" + evaluation +
                '}';
    }
}
