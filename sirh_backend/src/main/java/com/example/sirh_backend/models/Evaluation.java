package com.example.sirh_backend.models;

import jakarta.persistence.*;

import java.time.Year;
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
}
