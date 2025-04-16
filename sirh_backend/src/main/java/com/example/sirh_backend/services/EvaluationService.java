package com.example.sirh_backend.services;

import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.patterns.EvaluationReportPdfStrategy;
import com.example.sirh_backend.models.utils.EvaluationReportGenerator;
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

    public byte[] generatePdfReport(long evaluationId) {
        Evaluation evaluation = getEvaluationById(evaluationId);
        if (evaluation == null) {
            throw new IllegalArgumentException("Evaluation not found");
        }
        EvaluationReportGenerator evaluationReportGenerator = EvaluationReportGenerator.getInstance();
        evaluationReportGenerator.setStrategy(new EvaluationReportPdfStrategy());
        return evaluationReportGenerator.generateReport(evaluation);
    }
}
