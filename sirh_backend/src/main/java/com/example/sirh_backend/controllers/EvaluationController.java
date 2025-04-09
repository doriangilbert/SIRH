package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.EvaluationDTO;
import com.example.sirh_backend.models.Evaluation;
import com.example.sirh_backend.services.EvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("/evaluations")
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/evaluations/{id}")
    public EvaluationDTO getEvaluationById(@PathVariable long id) {
        return evaluationService.getEvaluationById(id);
    }

    @PostMapping("/evaluations")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    @PutMapping("/evaluations/{id}")
    public Evaluation updateEvaluation(@PathVariable long id, @RequestBody Evaluation evaluation) {
        return evaluationService.updateEvaluation(id, evaluation);
    }
}
