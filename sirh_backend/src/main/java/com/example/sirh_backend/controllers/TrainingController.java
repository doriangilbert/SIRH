package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.TrainingDTO;
import com.example.sirh_backend.models.Training;
import com.example.sirh_backend.services.TrainingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/trainings")
    public List<TrainingDTO> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/trainings/{id}")
    public TrainingDTO getTrainingById(@PathVariable long id) {
        return trainingService.getTrainingById(id);
    }

    @PostMapping("/trainings")
    public Training createTraining(@RequestBody Training training) {
        return trainingService.createTraining(training);
    }

    @PutMapping("/trainings/{id}")
    public Training updateTraining(@PathVariable long id, @RequestBody Training training) {
        return trainingService.updateTraining(id, training);
    }
}
