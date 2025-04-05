package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.FeedbackDTO;
import com.example.sirh_backend.models.Feedback;
import com.example.sirh_backend.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(feedback -> new FeedbackDTO(
                        feedback.getId(),
                        feedback.getDescription(),
                        feedback.getReviewer().getId(),
                        feedback.getEvaluation().getId()
                ))
                .toList();
    }

    public FeedbackDTO getFeedbackById(long id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback != null) {
            return new FeedbackDTO(
                    feedback.getId(),
                    feedback.getDescription(),
                    feedback.getReviewer().getId(),
                    feedback.getEvaluation().getId()
            );
        }
        return null;
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
}
