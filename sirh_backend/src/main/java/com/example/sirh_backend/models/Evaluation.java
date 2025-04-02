package com.example.sirh_backend.models;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Year year;

    private String description;

    @Enumerated(EnumType.STRING)
    private EvaluationStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL)
    private List<Objective> objectives;

    public Evaluation() {}

    public Evaluation(Year year, String description, Employee employee) {
        this.year = year;
        this.description = description;
        this.status = EvaluationStatus.WAITING;
        this.employee = employee;
        this.feedbacks = new ArrayList<>();
        this.objectives = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EvaluationStatus getStatus() {
        return status;
    }

    public void setStatus(EvaluationStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", employee=" + employee +
                ", feedbacks=" + feedbacks +
                ", objectives=" + objectives +
                '}';
    }
}
