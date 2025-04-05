package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.TeamDTO;
import com.example.sirh_backend.models.Employee;
import com.example.sirh_backend.models.Team;
import com.example.sirh_backend.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(team -> new TeamDTO(
                        team.getId(),
                        team.getName(),
                        team.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()),
                        team.getManager().getId()
                ))
                .collect(Collectors.toList());
    }

    public TeamDTO getTeamById(long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team != null) {
            return new TeamDTO(
                    team.getId(),
                    team.getName(),
                    team.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()),
                    team.getManager().getId()
            );
        }
        return null;
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }
}
