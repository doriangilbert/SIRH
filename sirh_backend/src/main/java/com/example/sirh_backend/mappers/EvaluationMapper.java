package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.EvaluationDTO;
import com.example.sirh_backend.models.Evaluation;
import com.example.sirh_backend.models.Feedback;
import com.example.sirh_backend.models.Objective;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EvaluationMapper {

    public static EvaluationDTO toDTO(Evaluation evaluation) {
        if (evaluation != null) {
            return new EvaluationDTO(
                    evaluation.getId(),
                    Integer.parseInt(evaluation.getYear().toString()),
                    evaluation.getDescription(),
                    evaluation.getStatus().toString(),
                    evaluation.getEmployee().getId(),
                    evaluation.getFeedbacks().stream().map(Feedback::getId).collect(Collectors.toList()),
                    evaluation.getObjectives().stream().map(Objective::getId).collect(Collectors.toList())
            );
        }
        return null;
    }

    public static List<EvaluationDTO> toDTO(List<Evaluation> evaluations) {
        return evaluations.stream()
                .map(EvaluationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
