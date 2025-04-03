package com.example.sirh_backend.controllers;

import com.example.sirh_backend.models.Objective;
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
    public List<Objective> getAllObjectives() {
        return objectiveService.getAllObjectives();
    }

    @GetMapping("/objectives/{id}")
    public Objective getObjectiveById(@PathVariable long id) {
        return objectiveService.getObjectiveById(id);
    }

    @PostMapping("/objectives")
    public Objective createObjective(@RequestBody Objective objective) {
        return objectiveService.createObjective(objective);
    }
}
