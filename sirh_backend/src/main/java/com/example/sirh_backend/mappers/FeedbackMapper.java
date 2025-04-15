package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.FeedbackDTO;
import com.example.sirh_backend.models.entities.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {

    public static FeedbackDTO toDTO(Feedback feedback) {
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

    public static List<FeedbackDTO> toDTO(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }
}
