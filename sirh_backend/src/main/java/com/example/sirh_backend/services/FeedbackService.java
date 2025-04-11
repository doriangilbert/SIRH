package com.example.sirh_backend.services;

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

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(long id, Feedback updatedFeedback) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback != null) {
            feedback.setDescription(updatedFeedback.getDescription());
            feedback.setReviewer(updatedFeedback.getReviewer());
            feedback.setEvaluation(updatedFeedback.getEvaluation());
            return feedbackRepository.save(feedback);
        }
        return null;
    }
}
