package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.EvaluationDTO;
import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.entities.Feedback;
import com.example.sirh_backend.models.entities.Objective;

import java.util.List;

public class EvaluationMapper {

    private EvaluationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static EvaluationDTO toDTO(Evaluation evaluation) {
        if (evaluation != null) {
            return new EvaluationDTO(
                    evaluation.getId(),
                    Integer.parseInt(evaluation.getYear().toString()),
                    evaluation.getDescription(),
                    evaluation.getStatus() != null ? evaluation.getStatus().toString() : null,
                    evaluation.getEmployee() != null ? evaluation.getEmployee().getId() : null,
                    evaluation.getFeedbacks() != null ? evaluation.getFeedbacks().stream().map(Feedback::getId).toList() : List.of(),
                    evaluation.getObjectives() != null ? evaluation.getObjectives().stream().map(Objective::getId).toList() : List.of()
            );
        }
        return null;
    }

    public static List<EvaluationDTO> toDTO(List<Evaluation> evaluations) {
        return evaluations.stream()
                .map(EvaluationMapper::toDTO)
                .toList();
    }
}
