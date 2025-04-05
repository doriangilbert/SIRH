package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.FeedbackDTO;
import com.example.sirh_backend.models.Feedback;
import com.example.sirh_backend.services.FeedbackService;
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
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/feedbacks/{id}")
    public FeedbackDTO getFeedbackById(@PathVariable long id) {
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping("/feedbacks")
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }
}
