package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Skill;
import com.example.sirh_backend.repositories.SkillRepository;
import com.example.sirh_backend.services.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    private Skill testSkill;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testSkill = new Skill();
        testSkill.setName("Java");
    }

    @Test
    void getAllSkillsReturnsAllSkills() {
        when(skillRepository.findAll()).thenReturn(List.of(testSkill));

        var skills = skillService.getAllSkills();

        assertNotNull(skills);
        assertEquals(1, skills.size());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void getSkillByIdReturnsCorrectSkill() {
        when(skillRepository.findById(1L)).thenReturn(Optional.of(testSkill));

        var skill = skillService.getSkillById(1L);

        assertNotNull(skill);
        assertEquals("Java", skill.getName());
        verify(skillRepository, times(1)).findById(1L);
    }

    @Test
    void getSkillByIdThrowsNotFoundExceptionForInvalidId() {
        when(skillRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> skillService.getSkillById(999L));
        verify(skillRepository, times(1)).findById(999L);
    }

    @Test
    void createSkillSavesAndReturnsSkill() {
        when(skillRepository.save(testSkill)).thenReturn(testSkill);

        var savedSkill = skillService.createSkill(testSkill);

        assertNotNull(savedSkill);
        assertEquals("Java", savedSkill.getName());
        verify(skillRepository, times(1)).save(testSkill);
    }

    @Test
    void updateSkillUpdatesSkillDetails() {
        when(skillRepository.findById(1L)).thenReturn(Optional.of(testSkill));
        when(skillRepository.save(testSkill)).thenReturn(testSkill);

        testSkill.setName("Updated Skill");
        var updatedSkill = skillService.updateSkill(1L, testSkill);

        assertEquals("Updated Skill", updatedSkill.getName());
        verify(skillRepository, times(1)).findById(1L);
        verify(skillRepository, times(1)).save(testSkill);
    }

    @Test
    void updateSkillThrowsIllegalArgumentExceptionForNullUpdatedSkill() {
        when(skillRepository.findById(1L)).thenReturn(Optional.of(testSkill));

        assertThrows(IllegalArgumentException.class, () -> skillService.updateSkill(1L, null));
        verify(skillRepository, times(1)).findById(1L);
    }

    @Test
    void updateSkillThrowsNotFoundExceptionForInvalidId() {
        when(skillRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedSkill = new Skill();
        updatedSkill.setName("Invalid");

        assertThrows(NotFoundException.class, () -> skillService.updateSkill(999L, updatedSkill));
        verify(skillRepository, times(1)).findById(999L);
    }
}