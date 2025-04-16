package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;
    private Position position;
    private Team team;
    private Skill skill;
    private Notification notification;

    @BeforeEach
    void setUp() {
        position = new Position("Manager");
        team = new Team("Development", null);
        employee = new Employee("John", "Doe", position, team);
        skill = new Skill("Java");
        notification = new Notification("Type", "Message", employee);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals(0, employee.getLeaveBalance());
        assertEquals(position, employee.getPosition());
        assertEquals(team, employee.getTeam());
        assertNotNull(employee.getSkills());
        assertNotNull(employee.getNotifications());
    }

    @Test
    void getFirstNameReturnsCorrectFirstName() {
        assertEquals("John", employee.getFirstName());
    }

    @Test
    void setFirstNameUpdatesFirstName() {
        employee.setFirstName("Jane");
        assertEquals("Jane", employee.getFirstName());
    }

    @Test
    void getLastNameReturnsCorrectLastName() {
        assertEquals("Doe", employee.getLastName());
    }

    @Test
    void setLastNameUpdatesLastName() {
        employee.setLastName("Smith");
        assertEquals("Smith", employee.getLastName());
    }

    @Test
    void getLeaveBalanceReturnsCorrectLeaveBalance() {
        employee.setLeaveBalance(15);
        assertEquals(15, employee.getLeaveBalance());
    }

    @Test
    void setLeaveBalanceUpdatesValueWhenPositive() {
        employee.setLeaveBalance(25);
        assertEquals(25, employee.getLeaveBalance());
    }

    @Test
    void setLeaveBalanceThrowsExceptionWhenNegative() {
        assertThrows(IllegalArgumentException.class, () -> employee.setLeaveBalance(-1));
    }

    @Test
    void getPositionReturnsCorrectPosition() {
        assertEquals(position, employee.getPosition());
    }

    @Test
    void setPositionUpdatesPosition() {
        Position newPosition = new Position("Developer");
        employee.setPosition(newPosition);
        assertEquals(newPosition, employee.getPosition());
    }

    @Test
    void setSkillsUpdatesSkillsList() {
        Skill skill2 = new Skill("Python");
        employee.setSkills(List.of(skill, skill2));
        assertEquals(2, employee.getSkills().size());
        assertTrue(employee.getSkills().contains(skill));
        assertTrue(employee.getSkills().contains(skill2));
    }

    @Test
    void getTeamReturnsCorrectTeam() {
        assertEquals(team, employee.getTeam());
    }

    @Test
    void setTeamUpdatesTeam() {
        Team newTeam = new Team("Testing", null);
        employee.setTeam(newTeam);
        assertEquals(newTeam, employee.getTeam());
    }

    @Test
    void setNotificationsUpdatesNotificationsList() {
        Notification notification2 = new Notification("Type2", "Message2", employee);
        employee.setNotifications(List.of(notification, notification2));
        assertEquals(2, employee.getNotifications().size());
        assertTrue(employee.getNotifications().contains(notification));
        assertTrue(employee.getNotifications().contains(notification2));
    }

    @Test
    void addSkillAddsSkillWhenNotAlreadyPresent() {
        employee.addSkill(skill);
        assertTrue(employee.getSkills().contains(skill));
    }

    @Test
    void addSkillThrowsExceptionWhenSkillAlreadyExists() {
        employee.addSkill(skill);
        assertThrows(IllegalArgumentException.class, () -> employee.addSkill(skill));
    }

    @Test
    void removeSkillRemovesSkillWhenPresent() {
        employee.addSkill(skill);
        employee.removeSkill(skill);
        assertFalse(employee.getSkills().contains(skill));
    }

    @Test
    void removeSkillThrowsExceptionWhenSkillNotPresent() {
        assertThrows(IllegalArgumentException.class, () -> employee.removeSkill(skill));
    }

    @Test
    void addNotificationAddsNotificationWhenNotAlreadyPresent() {
        employee.addNotification(notification);
        assertTrue(employee.getNotifications().contains(notification));
    }

    @Test
    void addNotificationThrowsExceptionWhenNotificationAlreadyExists() {
        employee.addNotification(notification);
        assertThrows(IllegalArgumentException.class, () -> employee.addNotification(notification));
    }

    @Test
    void removeNotificationRemovesNotificationWhenPresent() {
        employee.addNotification(notification);
        employee.removeNotification(notification);
        assertFalse(employee.getNotifications().contains(notification));
    }

    @Test
    void removeNotificationThrowsExceptionWhenNotificationNotPresent() {
        assertThrows(IllegalArgumentException.class, () -> employee.removeNotification(notification));
    }
}