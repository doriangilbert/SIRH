package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.TeamDTO;
import com.example.sirh_backend.mappers.TeamMapper;
import com.example.sirh_backend.models.entities.Team;
import com.example.sirh_backend.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return new ResponseEntity<>(TeamMapper.toDTO(teamService.getAllTeams()), HttpStatus.OK);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable long id) {
        return new ResponseEntity<>(TeamMapper.toDTO(teamService.getTeamById(id)), HttpStatus.OK);
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody Team team) {
        return new ResponseEntity<>(TeamMapper.toDTO(teamService.createTeam(team)), HttpStatus.CREATED);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable long id, @RequestBody Team team) {
        return new ResponseEntity<>(TeamMapper.toDTO(teamService.updateTeam(id, team)), HttpStatus.OK);
    }
}
