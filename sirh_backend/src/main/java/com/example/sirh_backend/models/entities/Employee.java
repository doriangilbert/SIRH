package com.example.sirh_backend.models.entities;

import com.example.sirh_backend.models.patterns.Observer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee implements Observer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private int leaveBalance;

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

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public Employee() {}

    public Employee(String firstName, String lastName, Position position, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaveBalance = 0;
        this.position = position;
        this.skills = new ArrayList<>();
        this.team = team;
        this.notifications = new ArrayList<>();
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

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", leaveBalance=" + leaveBalance +
                ", position=" + position +
                ", skills=" + skills +
                ", team=" + team +
                ", notifications=" + notifications +
                '}';
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

    public void addNotification(Notification notification) {
        if (this.notifications.contains(notification)) {
            throw new IllegalArgumentException("Notification already exists");
        } else {
            this.notifications.add(notification);
        }
    }

    public void removeNotification(Notification notification) {
        if (!this.notifications.contains(notification)) {
            throw new IllegalArgumentException("Notification does not exist");
        } else {
            this.notifications.remove(notification);
        }
    }

    @Override
    public void update(String message) {
        Notification notification = new Notification("Update", message, this);
        this.notifications.add(notification);
    }
}
