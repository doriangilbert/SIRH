package com.example.sirh_backend.models.utils;

import com.example.sirh_backend.models.entities.Evaluation;
import com.example.sirh_backend.models.patterns.EvaluationReportPdfBuilder;

import java.io.IOException;

public class EvaluationReportGenerator {

    private static EvaluationReportGenerator instance;

    private EvaluationReportGenerator() {}

    public static synchronized EvaluationReportGenerator getInstance() {
        if (instance == null) {
            instance = new EvaluationReportGenerator();
        }
        return instance;
    }

    public byte[] generatePdfReport(Evaluation evaluation) {
        EvaluationReportPdfBuilder builder = new EvaluationReportPdfBuilder();
        builder.setupReport();
        builder.addBasicInformation(evaluation);
        builder.addObjectives(evaluation);
        builder.addFeedbacks(evaluation);
        return builder.buildReport();
    }
}
