package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.EvaluationDTO;
import com.example.sirh_backend.models.Evaluation;
import com.example.sirh_backend.models.Feedback;
import com.example.sirh_backend.models.Objective;
import com.example.sirh_backend.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationRepository.findAll().stream()
                .map(evaluation -> new EvaluationDTO(
                        evaluation.getId(),
                        Integer.parseInt(evaluation.getYear().toString()),
                        evaluation.getDescription(),
                        evaluation.getStatus().toString(),
                        evaluation.getEmployee().getId(),
                        evaluation.getFeedbacks().stream().map(Feedback::getId).collect(Collectors.toList()),
                        evaluation.getObjectives().stream().map(Objective::getId).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public EvaluationDTO getEvaluationById(long id) {
        Evaluation evaluation = evaluationRepository.findById(id).orElse(null);
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

    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation updateEvaluation(long id, Evaluation updatedEvaluation) {
        Evaluation evaluation = evaluationRepository.findById(id).orElse(null);
        if (evaluation != null) {
            evaluation.setYear(updatedEvaluation.getYear());
            evaluation.setDescription(updatedEvaluation.getDescription());
            evaluation.setStatus(updatedEvaluation.getStatus());
            evaluation.setEmployee(updatedEvaluation.getEmployee());
            evaluation.setFeedbacks(updatedEvaluation.getFeedbacks());
            evaluation.setObjectives(updatedEvaluation.getObjectives());
            return evaluationRepository.save(evaluation);
        }
        return null;
    }
}
