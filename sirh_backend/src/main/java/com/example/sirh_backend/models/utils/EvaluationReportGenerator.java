package com.example.sirh_backend.models.utils;

import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.patterns.EvaluationReportPdfBuilder;
import com.example.sirh_backend.models.patterns.EvaluationReportStrategy;

import java.io.IOException;

public class EvaluationReportGenerator {

    private static EvaluationReportGenerator instance;
    private EvaluationReportStrategy strategy;

    private EvaluationReportGenerator() {}

    public static synchronized EvaluationReportGenerator getInstance() {
        if (instance == null) {
            instance = new EvaluationReportGenerator();
        }
        return instance;
    }

    public void setStrategy(EvaluationReportStrategy strategy) {
        this.strategy = strategy;
    }

    public byte[] generateReport(Evaluation evaluation) {
        if (strategy == null) {
            throw new IllegalStateException("A strategy must be set before generating a report");
        }
        return strategy.generateReport(evaluation);
    }
}
