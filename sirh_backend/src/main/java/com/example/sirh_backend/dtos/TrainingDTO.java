package com.example.sirh_backend.dtos;

import java.util.List;

public class TrainingDTO {

    private Long id;
    private String name;
    private String description;
    private List<Long> employees;
    private List<Long> skills;

    public TrainingDTO(Long id, String name, String description, List<Long> employees, List<Long> skills) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employees = employees;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Long> employees) {
        this.employees = employees;
    }

    public List<Long> getSkills() {
        return skills;
    }

    public void setSkills(List<Long> skills) {
        this.skills = skills;
    }
}
