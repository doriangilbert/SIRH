package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.FeedbackDTO;
import com.example.sirh_backend.models.entities.Feedback;

import java.util.List;

public class FeedbackMapper {

    private FeedbackMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static FeedbackDTO toDTO(Feedback feedback) {
        if (feedback != null) {
            return new FeedbackDTO(
                    feedback.getId(),
                    feedback.getDescription(),
                    feedback.getReviewer() != null ? feedback.getReviewer().getId() : null,
                    feedback.getEvaluation() != null ? feedback.getEvaluation().getId() : null
            );
        }
        return null;
    }

    public static List<FeedbackDTO> toDTO(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .map(FeedbackMapper::toDTO)
                .toList();
    }
}
