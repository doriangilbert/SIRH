package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.TeamDTO;
import com.example.sirh_backend.models.Team;
import com.example.sirh_backend.services.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/{id}")
    public TeamDTO getTeamById(@PathVariable long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }
}
