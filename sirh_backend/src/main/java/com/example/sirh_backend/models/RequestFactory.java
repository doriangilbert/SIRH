package com.example.sirh_backend.models;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RequestFactory {

    public static Request createRequest(String requestType, Object... params) {
        switch (requestType) {
            case "LeaveRequest":
                if (params.length == 3 && params[0] instanceof LocalDate && params[1] instanceof LocalDate && params[2] instanceof Employee) {
                    return new LeaveRequest((LocalDate) params[0], (LocalDate) params[1], (Employee) params[2]);
                }
                throw new IllegalArgumentException("Invalid parameters for LeaveRequest");

            case "TrainingRequest":
                if (params.length == 2 && params[0] instanceof Training && params[1] instanceof Employee) {
                    return new TrainingRequest((Training) params[0], (Employee) params[1]);
                }
                throw new IllegalArgumentException("Invalid parameters for TrainingRequest");

            default:
                throw new IllegalArgumentException("Request type does not exist");
        }
    }
}
