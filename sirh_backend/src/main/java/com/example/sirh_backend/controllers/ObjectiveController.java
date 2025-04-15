package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.ObjectiveDTO;
import com.example.sirh_backend.mappers.ObjectiveMapper;
import com.example.sirh_backend.models.entities.Objective;
import com.example.sirh_backend.services.ObjectiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ObjectiveController {

    private final ObjectiveService objectiveService;

    public ObjectiveController(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @GetMapping("/objectives")
    public ResponseEntity<List<ObjectiveDTO>> getAllObjectives() {
        return new ResponseEntity<>(ObjectiveMapper.toDTO(objectiveService.getAllObjectives()), HttpStatus.OK);
    }

    @GetMapping("/objectives/{id}")
    public ResponseEntity<ObjectiveDTO> getObjectiveById(@PathVariable long id) {
        return new ResponseEntity<>(ObjectiveMapper.toDTO(objectiveService.getObjectiveById(id)), HttpStatus.OK);
    }

    @PostMapping("/objectives")
    public ResponseEntity<ObjectiveDTO> createObjective(@RequestBody Objective objective) {
        return new ResponseEntity<>(ObjectiveMapper.toDTO(objectiveService.createObjective(objective)), HttpStatus.CREATED);
    }

    @PutMapping("/objectives/{id}")
    public ResponseEntity<ObjectiveDTO> updateObjective(@PathVariable long id, @RequestBody Objective objective) {
        return new ResponseEntity<>(ObjectiveMapper.toDTO(objectiveService.updateObjective(id, objective)), HttpStatus.OK);
    }
}
