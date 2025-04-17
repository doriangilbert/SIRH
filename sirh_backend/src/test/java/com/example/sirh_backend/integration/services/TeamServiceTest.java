package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Team;
import com.example.sirh_backend.repositories.TeamRepository;
import com.example.sirh_backend.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team testTeam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testTeam = new Team();
        testTeam.setName("Development Team");
    }

    @Test
    void getAllTeamsReturnsAllTeams() {
        when(teamRepository.findAll()).thenReturn(List.of(testTeam));

        var teams = teamService.getAllTeams();

        assertNotNull(teams);
        assertEquals(1, teams.size());
        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void getTeamByIdReturnsCorrectTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(testTeam));

        var team = teamService.getTeamById(1L);

        assertNotNull(team);
        assertEquals("Development Team", team.getName());
        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    void getTeamByIdThrowsNotFoundExceptionForInvalidId() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> teamService.getTeamById(999L));
        verify(teamRepository, times(1)).findById(999L);
    }

    @Test
    void createTeamSavesAndReturnsTeam() {
        when(teamRepository.save(testTeam)).thenReturn(testTeam);

        var savedTeam = teamService.createTeam(testTeam);

        assertNotNull(savedTeam);
        assertEquals("Development Team", savedTeam.getName());
        verify(teamRepository, times(1)).save(testTeam);
    }

    @Test
    void updateTeamUpdatesTeamDetails() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(testTeam));
        when(teamRepository.save(testTeam)).thenReturn(testTeam);

        testTeam.setName("Updated Team");
        var updatedTeam = teamService.updateTeam(1L, testTeam);

        assertEquals("Updated Team", updatedTeam.getName());
        verify(teamRepository, times(1)).findById(1L);
        verify(teamRepository, times(1)).save(testTeam);
    }

    @Test
    void updateTeamThrowsIllegalArgumentExceptionForNullUpdatedTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(testTeam));

        assertThrows(IllegalArgumentException.class, () -> teamService.updateTeam(1L, null));
        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    void updateTeamThrowsNotFoundExceptionForInvalidId() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedTeam = new Team();
        updatedTeam.setName("Invalid");

        assertThrows(NotFoundException.class, () -> teamService.updateTeam(999L, updatedTeam));
        verify(teamRepository, times(1)).findById(999L);
    }
    }