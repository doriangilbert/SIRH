package com.example.sirh_backend.dtos;

import java.util.List;

public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int leaveBalance;
    private Long position;
    private List<Long> skills;
    private Long team;
    private List<Long> notifications;

    public EmployeeDTO(Long id, String firstName, String lastName, int leaveBalance, Long position, List<Long> skills, Long team, List<Long> notifications) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaveBalance = leaveBalance;
        this.position = position;
        this.skills = skills;
        this.team = team;
        this.notifications = notifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public List<Long> getSkills() {
        return skills;
    }

    public void setSkills(List<Long> skills) {
        this.skills = skills;
    }

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }

    public List<Long> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Long> notifications) {
        this.notifications = notifications;
    }
}
