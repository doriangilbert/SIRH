package com.example.sirh_backend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "training_employee",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "training_skill",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    public Training() {}

    public Training(String name, String description) {
        this.name = name;
        this.description = description;
        this.employees = new ArrayList<>();
        this.skills = new ArrayList<>();
    }

    public Long getId() {
        return id;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                ", skills=" + skills +
                '}';
    }

    public void addEmployee(Employee employee) {
        if (this.employees.contains(employee)) {
            throw new IllegalArgumentException("Employee already exists");
        } else {
            this.employees.add(employee);
        }
    }

    public void removeEmployee(Employee employee) {
        if (!this.employees.contains(employee)) {
            throw new IllegalArgumentException("Employee does not exist");
        } else {
            this.employees.remove(employee);
        }
    }

    public void addSkill(Skill skill) {
        if (this.skills.contains(skill)) {
            throw new IllegalArgumentException("Skill already exists");
        } else {
            this.skills.add(skill);
        }
    }

    public void removeSkill(Skill skill) {
        if (!this.skills.contains(skill)) {
            throw new IllegalArgumentException("Skill does not exist");
        } else {
            this.skills.remove(skill);
        }
    }
}
