package com.example.sirh_backend.models.patterns;

import com.example.sirh_backend.models.entities.Evaluation;

public interface EvaluationReportBuilder {

    void setupReport();
    void addBasicInformation(Evaluation evaluation);
    void addObjectives(Evaluation evaluation);
    void addFeedbacks(Evaluation evaluation);
    byte[] buildReport();
}
