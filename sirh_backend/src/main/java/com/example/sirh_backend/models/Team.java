package com.example.sirh_backend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
}
