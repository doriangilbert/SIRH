package com.example.sirh_backend.models.patterns;

import com.example.sirh_backend.models.entities.Evaluation;

public class EvaluationReportPdfStrategy implements EvaluationReportStrategy {

    @Override
    public byte[] generateReport(Evaluation evaluation) {
        EvaluationReportPdfBuilder builder = new EvaluationReportPdfBuilder();
        builder.setupReport();
        builder.addBasicInformation(evaluation);
        builder.addObjectives(evaluation);
        builder.addFeedbacks(evaluation);
        return builder.buildReport();
    }
}
