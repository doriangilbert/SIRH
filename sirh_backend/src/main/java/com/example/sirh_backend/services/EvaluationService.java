package com.example.sirh_backend.services;

import com.example.sirh_backend.models.Evaluation;
import com.example.sirh_backend.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Evaluation getEvaluationById(long id) {
        return evaluationRepository.findById(id).orElse(null);
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
