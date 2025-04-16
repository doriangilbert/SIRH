package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.TeamDTO;
import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Team;

import java.util.List;

public class TeamMapper {

    private TeamMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static TeamDTO toDTO(Team team) {
        if (team != null) {
            return new TeamDTO(
                    team.getId(),
                    team.getName(),
                    team.getEmployees() != null ? team.getEmployees().stream().map(Employee::getId).toList() : null,
                    team.getManager() != null ? team.getManager().getId() : null
            );
        }
        return null;
    }

    public static List<TeamDTO> toDTO(List<Team> teams) {
        return teams.stream()
                .map(TeamMapper::toDTO)
                .toList();
    }
}
