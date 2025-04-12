package com.example.sirh_backend.dtos;

public class NotificationDTO {

    private Long id;
    private String title;
    private String description;
    private Long employee;

    public NotificationDTO(Long id, String title, String description, Long employee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }
}
