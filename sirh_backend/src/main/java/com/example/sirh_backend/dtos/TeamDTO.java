package com.example.sirh_backend.dtos;

import java.util.List;

public class TeamDTO {

    private Long id;
    private String name;
    private List<Long> employeesIds;
    private Long managerId;

    public TeamDTO(Long id, String name, List<Long> employeesIds, Long managerId) {
        this.id = id;
        this.name = name;
        this.employeesIds = employeesIds;
        this.managerId = managerId;
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

    public List<Long> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(List<Long> employeesIds) {
        this.employeesIds = employeesIds;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
