package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.FeedbackDTO;
import com.example.sirh_backend.mappers.FeedbackMapper;
import com.example.sirh_backend.models.entities.Feedback;
import com.example.sirh_backend.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return new ResponseEntity<>(FeedbackMapper.toDTO(feedbackService.getAllFeedbacks()), HttpStatus.OK);
    }

    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable long id) {
        return new ResponseEntity<>(FeedbackMapper.toDTO(feedbackService.getFeedbackById(id)), HttpStatus.OK);
    }

    @PostMapping("/feedbacks")
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody Feedback feedback) {
        return new ResponseEntity<>(FeedbackMapper.toDTO(feedbackService.createFeedback(feedback)), HttpStatus.CREATED);
    }

    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable long id, @RequestBody Feedback feedback) {
        return new ResponseEntity<>(FeedbackMapper.toDTO(feedbackService.updateFeedback(id, feedback)), HttpStatus.OK);
    }
}
