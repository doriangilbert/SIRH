package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.TrainingRequestDTO;
import com.example.sirh_backend.mappers.TrainingRequestMapper;
import com.example.sirh_backend.models.entities.TrainingRequest;
import com.example.sirh_backend.services.TrainingRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TrainingRequestDTO>> getAllTrainingRequests() {
        return new ResponseEntity<>(TrainingRequestMapper.toDTO(trainingRequestService.getAllTrainingRequests()), HttpStatus.OK);
    }

    @GetMapping("/trainingrequests/{id}")
    public ResponseEntity<TrainingRequestDTO> getTrainingRequestsById(@PathVariable long id) {
        return new ResponseEntity<>(TrainingRequestMapper.toDTO(trainingRequestService.getTrainingRequestById(id)), HttpStatus.OK);
    }

    @PostMapping("/trainingrequests")
    public ResponseEntity<TrainingRequestDTO> createTrainingRequest(@RequestBody TrainingRequest trainingRequest) {
        return new ResponseEntity<>(TrainingRequestMapper.toDTO(trainingRequestService.createTrainingRequest(trainingRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/trainingrequests/{id}")
    public ResponseEntity<TrainingRequestDTO> updateTrainingRequest(@PathVariable long id, @RequestBody TrainingRequest trainingRequest) {
        return new ResponseEntity<>(TrainingRequestMapper.toDTO(trainingRequestService.updateTrainingRequest(id, trainingRequest)), HttpStatus.OK);
    }

    @PutMapping("/trainingrequests/{id}/approve")
    public ResponseEntity<TrainingRequestDTO> approveTrainingRequest(@PathVariable long id, @RequestBody String requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            long reviewerId = jsonNode.get("reviewerId").asLong();
            return new ResponseEntity<>(TrainingRequestMapper.toDTO(trainingRequestService.approveTrainingRequest(id, reviewerId)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/trainingrequests/{id}/refuse")
    public ResponseEntity<TrainingRequestDTO> refuseTrainingRequest(@PathVariable long id, @RequestBody String requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            long reviewerId = jsonNode.get("reviewerId").asLong();
            return new ResponseEntity<>(TrainingRequestMapper.toDTO(trainingRequestService.refuseTrainingRequest(id, reviewerId)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
