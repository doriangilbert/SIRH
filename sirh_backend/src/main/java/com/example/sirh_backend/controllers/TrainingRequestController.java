package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.TrainingRequestDTO;
import com.example.sirh_backend.models.TrainingRequest;
import com.example.sirh_backend.services.TrainingRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TrainingRequestController {

    private final TrainingRequestService trainingRequestService;

    public TrainingRequestController(TrainingRequestService trainingRequestService) {
        this.trainingRequestService = trainingRequestService;
    }

    @GetMapping("/trainingrequests")
    public List<TrainingRequestDTO> getAllTrainingRequests() {
        return trainingRequestService.getAllTrainingRequests();
    }

    @GetMapping("/trainingrequests/{id}")
    public TrainingRequestDTO getTrainingRequestsById(@PathVariable long id) {
        return trainingRequestService.getTrainingRequestById(id);
    }

    @PostMapping("/trainingrequests")
    public TrainingRequest createTrainingRequest(@RequestBody TrainingRequest trainingRequest) {
        return trainingRequestService.createTrainingRequest(trainingRequest);
    }
}
