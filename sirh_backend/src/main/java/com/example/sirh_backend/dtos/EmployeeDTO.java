package com.example.sirh_backend.dtos;

import java.util.List;

public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int leaveBalance;
    private List<Long> leaveRequests;
    private Long position;
    private List<Long> skills;
    private Long team;

    public EmployeeDTO(Long id, String firstName, String lastName, int leaveBalance, List<Long> leaveRequests, Long position, List<Long> skills, Long team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaveBalance = leaveBalance;
        this.leaveRequests = leaveRequests;
        this.position = position;
        this.skills = skills;
        this.team = team;
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

    public List<Long> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<Long> leaveRequests) {
        this.leaveRequests = leaveRequests;
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
}
