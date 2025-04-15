package com.example.sirh_backend.models.patterns;

import com.example.sirh_backend.models.entities.Evaluation;

public interface EvaluationReportStrategy {

    byte[] generateReport(Evaluation evaluation);
}
