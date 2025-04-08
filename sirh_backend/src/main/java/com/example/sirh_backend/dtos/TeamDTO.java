package com.example.sirh_backend.dtos;

import java.util.List;

public class TeamDTO {

    private Long id;
    private String name;
    private List<Long> employees;
    private Long manager;

    public TeamDTO(Long id, String name, List<Long> employees, Long manager) {
        this.id = id;
        this.name = name;
        this.employees = employees;
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

    public List<Long> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Long> employees) {
        this.employees = employees;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }
}
