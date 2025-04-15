package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.ObjectiveDTO;
import com.example.sirh_backend.mappers.ObjectiveMapper;
import com.example.sirh_backend.models.entities.Objective;
import com.example.sirh_backend.services.ObjectiveService;
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
    public List<ObjectiveDTO> getAllObjectives() {
        return ObjectiveMapper.toDTO(objectiveService.getAllObjectives());
    }

    @GetMapping("/objectives/{id}")
    public ObjectiveDTO getObjectiveById(@PathVariable long id) {
        return ObjectiveMapper.toDTO(objectiveService.getObjectiveById(id));
    }

    @PostMapping("/objectives")
    public Objective createObjective(@RequestBody Objective objective) {
        return objectiveService.createObjective(objective);
    }

    @PutMapping("/objectives/{id}")
    public Objective updateObjective(@PathVariable long id, @RequestBody Objective objective) {
        return objectiveService.updateObjective(id, objective);
    }
}
