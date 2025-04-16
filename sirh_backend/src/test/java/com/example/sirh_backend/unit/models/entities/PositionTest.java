package com.example.sirh_backend.unit.models.entities;

import com.example.sirh_backend.models.entities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position("Manager");
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals("Manager", position.getName());
    }

    @Test
    void getNameReturnsCorrectName() {
        position.setName("Developer");
        assertEquals("Developer", position.getName());
    }

    @Test
    void setNameUpdatesName() {
        position.setName("Analyst");
        assertEquals("Analyst", position.getName());
    }
}