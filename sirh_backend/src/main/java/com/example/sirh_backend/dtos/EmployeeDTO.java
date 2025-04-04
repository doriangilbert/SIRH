package com.example.sirh_backend.dtos;

import java.util.List;

public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int leaveBalance;
    private List<Long> leavesIds;
    private Long positionId;
    private List<Long> skillsIds;
    private Long teamId;

    public EmployeeDTO(Long id, String firstName, String lastName, int leaveBalance, List<Long> leavesIds, Long positionId, List<Long> skillsIds, Long teamId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaveBalance = leaveBalance;
        this.leavesIds = leavesIds;
        this.positionId = positionId;
        this.skillsIds = skillsIds;
        this.teamId = teamId;
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

    public List<Long> getLeavesIds() {
        return leavesIds;
    }

    public void setLeavesIds(List<Long> leavesIds) {
        this.leavesIds = leavesIds;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public List<Long> getSkillsIds() {
        return skillsIds;
    }

    public void setSkillsIds(List<Long> skillsIds) {
        this.skillsIds = skillsIds;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
