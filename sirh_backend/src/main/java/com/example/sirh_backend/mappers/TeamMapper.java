package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.TeamDTO;
import com.example.sirh_backend.models.Employee;
import com.example.sirh_backend.models.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public static TeamDTO toDTO(Team team) {
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

    public static List<TeamDTO> toDTO(List<Team> teams) {
        return teams.stream()
                .map(TeamMapper::toDTO)
                .collect(Collectors.toList());
    }
}
