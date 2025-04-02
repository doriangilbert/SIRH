package com.example.sirh_backend.models;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Employee reviewer;

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;
}
