package com.example.sirh_backend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private int leaveBalance;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<LeaveRequest> leaveRequests;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToMany
    @JoinTable(
            name = "employee_skill",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Employee() {}

    public Employee(String firstName, String lastName, Position position, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaveBalance = 0;
        this.leaveRequests = new ArrayList<>();
        this.position = position;
        this.skills = new ArrayList<>();
        this.team = team;
    }

    public Long getId() {
        return id;
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

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", leaveBalance=" + leaveBalance +
                ", leaveRequests=" + leaveRequests +
                ", position=" + position +
                ", skills=" + skills +
                ", team=" + team +
                '}';
    }

    public void addLeaveRequest(LeaveRequest leaveRequest) {
        if (this.leaveRequests.contains(leaveRequest)) {
            throw new IllegalArgumentException("LeaveRequest already exists");
        } else {
            this.leaveRequests.add(leaveRequest);
        }
    }

    public void removeLeaveRequest(LeaveRequest leaveRequest) {
        if (!this.leaveRequests.contains(leaveRequest)) {
            throw new IllegalArgumentException("LeaveRequest does not exist");
        } else {
            this.leaveRequests.remove(leaveRequest);
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
