package com.example.sirh_backend.models.patterns;

import com.example.sirh_backend.models.entities.*;

import java.time.LocalDate;

public class RequestFactory {

    private RequestFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static Request createRequest(String requestType, Object... params) {
        switch (requestType) {
            case "LeaveRequest":
                if (params.length == 3 && params[0] instanceof LocalDate startDate && params[1] instanceof LocalDate endDate && params[2] instanceof Employee employee) {
                    return new LeaveRequest(startDate, endDate, employee);
                }
                throw new IllegalArgumentException("Invalid parameters for LeaveRequest");

            case "TrainingRequest":
                if (params.length == 2 && params[0] instanceof Training training && params[1] instanceof Employee employee) {
                    return new TrainingRequest(training, employee);
                }
                throw new IllegalArgumentException("Invalid parameters for TrainingRequest");

            default:
                throw new IllegalArgumentException("Request type does not exist");
        }
    }
}
