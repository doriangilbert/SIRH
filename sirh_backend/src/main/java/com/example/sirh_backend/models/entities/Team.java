package com.example.sirh_backend.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
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

    public Team() {
        this.employees = new ArrayList<>();
    }

    public Team(String name, Employee manager) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.manager = manager;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", manager=" + manager +
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
}
