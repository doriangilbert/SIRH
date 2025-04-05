package com.example.sirh_backend.dtos;

import java.util.List;

public class TrainingDTO {

    private Long id;
    private String name;
    private String description;
    private List<Long> employeesIds;
    private List<Long> skillsIds;

    public TrainingDTO(Long id, String name, String description, List<Long> employeesIds, List<Long> skillsIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employeesIds = employeesIds;
        this.skillsIds = skillsIds;
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

    public List<Long> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(List<Long> employeesIds) {
        this.employeesIds = employeesIds;
    }

    public List<Long> getSkillsIds() {
        return skillsIds;
    }

    public void setSkillsIds(List<Long> skillsIds) {
        this.skillsIds = skillsIds;
    }
}
