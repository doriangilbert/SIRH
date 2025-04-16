package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SkillTest {

    private Skill skill;

    @BeforeEach
    void setUp() {
        skill = new Skill("Java");
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("Java", skill.getName());
    }

    @Test
    void getNameReturnsCorrectName() {
        skill.setName("Spring Boot");
        assertEquals("Spring Boot", skill.getName());
    }

    @Test
    void setNameUpdatesName() {
        skill.setName("Hibernate");
        assertEquals("Hibernate", skill.getName());
    }
}