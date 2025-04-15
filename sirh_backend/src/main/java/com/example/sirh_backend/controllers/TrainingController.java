package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.TrainingDTO;
import com.example.sirh_backend.mappers.TrainingMapper;
import com.example.sirh_backend.models.entities.Training;
import com.example.sirh_backend.services.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TrainingDTO>> getAllTrainings() {
        return new ResponseEntity<>(TrainingMapper.toDTO(trainingService.getAllTrainings()), HttpStatus.OK);
    }

    @GetMapping("/trainings/{id}")
    public ResponseEntity<TrainingDTO> getTrainingById(@PathVariable long id) {
        return new ResponseEntity<>(TrainingMapper.toDTO(trainingService.getTrainingById(id)), HttpStatus.OK);
    }

    @PostMapping("/trainings")
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody Training training) {
        return new ResponseEntity<>(TrainingMapper.toDTO(trainingService.createTraining(training)), HttpStatus.CREATED);
    }

    @PutMapping("/trainings/{id}")
    public ResponseEntity<TrainingDTO> updateTraining(@PathVariable long id, @RequestBody Training training) {
        return new ResponseEntity<>(TrainingMapper.toDTO(trainingService.updateTraining(id, training)), HttpStatus.OK);
    }
}
