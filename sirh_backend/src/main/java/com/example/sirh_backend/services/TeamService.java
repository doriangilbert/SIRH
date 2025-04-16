package com.example.sirh_backend.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Team;
import com.example.sirh_backend.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(long id) {
        return teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(long id, Team updatedTeam) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team not found"));
        if (updatedTeam == null) {
            throw new IllegalArgumentException("Updated team data cannot be null");
        }
        team.setName(updatedTeam.getName());
        team.setEmployees(updatedTeam.getEmployees());
        team.setManager(updatedTeam.getManager());
        return teamRepository.save(team);
    }
}
