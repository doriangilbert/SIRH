package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.TrainingRequestDTO;
import com.example.sirh_backend.mappers.TrainingRequestMapper;
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
        return TrainingRequestMapper.toDTO(trainingRequestService.getAllTrainingRequests());
    }

    @GetMapping("/trainingrequests/{id}")
    public TrainingRequestDTO getTrainingRequestsById(@PathVariable long id) {
        return TrainingRequestMapper.toDTO(trainingRequestService.getTrainingRequestById(id));
    }

    @PostMapping("/trainingrequests")
    public TrainingRequest createTrainingRequest(@RequestBody TrainingRequest trainingRequest) {
        return trainingRequestService.createTrainingRequest(trainingRequest);
    }

    @PutMapping("/trainingrequests/{id}")
    public TrainingRequest updateTrainingRequest(@PathVariable long id, @RequestBody TrainingRequest trainingRequest) {
        return trainingRequestService.updateTrainingRequest(id, trainingRequest);
    }

    @PutMapping("/trainingrequests/{id}/approve")
    public TrainingRequest approveTrainingRequest(@PathVariable long id, @RequestParam("reviewerId") long reviewerId) {
        return trainingRequestService.approveTrainingRequest(id, reviewerId);
    }

    @PutMapping("/trainingrequests/{id}/refuse")
    public TrainingRequest refuseTrainingRequest(@PathVariable long id, @RequestParam("reviewerId") long reviewerId) {
        return trainingRequestService.refuseTrainingRequest(id, reviewerId);
    }
}
