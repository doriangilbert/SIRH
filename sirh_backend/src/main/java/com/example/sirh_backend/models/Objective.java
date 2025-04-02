package com.example.sirh_backend.models;

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
}
