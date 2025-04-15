package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.EvaluationDTO;
import com.example.sirh_backend.mappers.EvaluationMapper;
import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.services.EvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EvaluationDTO>> getAllEvaluations() {
        return new ResponseEntity<>(EvaluationMapper.toDTO(evaluationService.getAllEvaluations()), HttpStatus.OK);
    }

    @GetMapping("/evaluations/{id}")
    public ResponseEntity<EvaluationDTO> getEvaluationById(@PathVariable long id) {
        return new ResponseEntity<>(EvaluationMapper.toDTO(evaluationService.getEvaluationById(id)), HttpStatus.OK);
    }

    @PostMapping("/evaluations")
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody Evaluation evaluation) {
        return new ResponseEntity<>(EvaluationMapper.toDTO(evaluationService.createEvaluation(evaluation)), HttpStatus.CREATED);
    }

    @PutMapping("/evaluations/{id}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(@PathVariable long id, @RequestBody Evaluation evaluation) {
        return new ResponseEntity<>(EvaluationMapper.toDTO(evaluationService.updateEvaluation(id, evaluation)), HttpStatus.OK);
    }
}
