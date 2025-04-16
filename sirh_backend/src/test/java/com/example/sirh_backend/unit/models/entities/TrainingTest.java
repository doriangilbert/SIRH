package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Skill;
import com.example.sirh_backend.models.entities.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTest {

    private Training training;
    private Skill skill;
    private List<Skill> skills;

    @BeforeEach
    void setUp() {
        training = new Training("Java Basics", "Learn the basics of Java");
        skill = new Skill("Default Skill");
        skills = new ArrayList<>();
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("Java Basics", training.getName());
        assertEquals("Learn the basics of Java", training.getDescription());
        assertNotNull(training.getSkills());
        assertTrue(training.getSkills().isEmpty());
    }

    @Test
    void getNameReturnsCorrectName() {
        training.setName("Spring Boot");
        assertEquals("Spring Boot", training.getName());
    }

    @Test
    void setNameUpdatesName() {
        training.setName("Hibernate");
        assertEquals("Hibernate", training.getName());
    }

    @Test
    void getDescriptionReturnsCorrectDescription() {
        training.setDescription("Advanced Java");
        assertEquals("Advanced Java", training.getDescription());
    }

    @Test
    void setDescriptionUpdatesDescription() {
        training.setDescription("Microservices with Spring");
        assertEquals("Microservices with Spring", training.getDescription());
    }

    @Test
    void getSkillsReturnsCorrectSkills() {
        skills.add(new Skill("Java"));
        training.setSkills(skills);
        assertEquals(skills, training.getSkills());
    }

    @Test
    void setSkillsUpdatesSkills() {
        skills.add(new Skill("Docker"));
        training.setSkills(skills);
        assertEquals(skills, training.getSkills());
    }

    @Test
    void addSkillAddsSkillToList() {
        training.addSkill(skill);
        assertTrue(training.getSkills().contains(skill));
    }

    @Test
    void addSkillThrowsExceptionIfSkillAlreadyExists() {
        training.addSkill(skill);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> training.addSkill(skill));
        assertEquals("Skill already exists", exception.getMessage());
    }

    @Test
    void removeSkillRemovesSkillFromList() {
        training.addSkill(skill);
        training.removeSkill(skill);
        assertFalse(training.getSkills().contains(skill));
    }

    @Test
    void removeSkillThrowsExceptionIfSkillDoesNotExist() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> training.removeSkill(skill));
        assertEquals("Skill does not exist", exception.getMessage());
    }
}